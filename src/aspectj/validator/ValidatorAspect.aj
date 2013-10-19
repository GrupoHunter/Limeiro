package aspectj.validator;

import java.util.HashMap;
import java.util.Map;

public aspect ValidatorAspect {

	pointcut validateObject(Object target, String newValue) : 
		set( * *..* ) && args(newValue) && target(target);

	before(Object target, String newValue): validateObject(target, newValue){
		String property = thisJoinPoint.getSignature().getName();
		if(record.containsKey(target))
			this.toValidate(target, property, newValue);
	}
	
	public void addValidator(Object target, String property, Validator<String> anValidator) {
		if (record.containsKey(target))
			record.get(target).put(property, anValidator);
		else {
			record.put(target, new HashMap<String, Validator<String>>());
			record.get(target).put(property, anValidator);
		}
	}

	// *************Fixture*************************
	
	 private Map<Object, Map<String, Validator<String>>> record = new HashMap<Object, Map<String, Validator<String>>>();
	
	 protected void toValidate(Object target, String property, String newValue) throws InvalidValueException {
		 Validator<String> validateTarget = record.get(target).containsKey(property)? record.get(target).get(property) : null;
		 if(!validateTarget.validate(newValue) && validateTarget != null)
			 throw new InvalidValueException();
	 }	
}