package aspectj.validator;

import java.lang.reflect.Field;

import aspectj.annotation.Monitored;

public aspect ValidatorAspect {

	pointcut validateObject(Object target, Object newValue) : 
		call(@Monitored * *(..)) && args(newValue) && target(target);

	void around(Object target, Object newValue): validateObject(target, newValue){
		String oldValue = (String) getValue(target);
		proceed(target, newValue);
		String newVal = (String) getValue(target);
		if(v != null) // Avoid problems with other test 
			this.toValidate(newVal,oldValue);
	}
	
	public void addValidator(Object target, String property, Validator<String> anValidator) {
		this.v = anValidator;
		this.object = target;
		this.monitored = property;
	}

	// *************Fixture*************************
	private Validator<String> v;
	private Object object;
	private String monitored; 

	protected void rollbackValue(String value){
		try {
			Field fieldName = object.getClass().getDeclaredField(monitored);
			fieldName.setAccessible(true);
			fieldName.set(object, value);
		} catch (Throwable e) {
		}
	}
	
	protected void toValidate(String anValue, String oldValue) throws InvalidValueException {
		if (!v.validate(anValue)){
			rollbackValue(oldValue);
			throw new InvalidValueException();
		}
	}
	
	protected Object getValue(Object oldValue) {
		Object value = null;
		try {
			Field fieldName = oldValue.getClass().getDeclaredField(monitored);
			fieldName.setAccessible(true);
			value = fieldName.get(oldValue);
		} catch (Throwable e) {
		}
		return value;
	}
}