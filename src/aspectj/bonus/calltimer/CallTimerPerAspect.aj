package aspectj.bonus.calltimer;

import java.util.HashMap;
import java.util.Map;

import aspectj.annotation.Monitored;

public aspect CallTimerPerAspect pertarget(call(@Monitored * *(..)) ){
//TODO Check it!!!
	pointcut callMethod(Object target) : 
		call(@Monitored * *(..)) && target(target);
	
	before(Object target): callMethod(target){
		String varName = thisJoinPoint.getSignature().getName();
		this.saveCall(varName);
	}

	public int amountCall(String propertyName) {
		return mapProperty.get(propertyName) == null ? 0 : mapProperty.get(propertyName);
	}
	
	// **************FIXTURE************************
	protected Map<String, Integer> mapProperty = new HashMap<String, Integer>();

	/**
	 * Save calls methods of objects that have been made
	 */
	protected void saveCall(String propertyName){
		if(!mapProperty.containsKey(propertyName)){ //Create a new key and save a value to access
			mapProperty.put(propertyName, 1);
		}else{ //Increments the access to that property
			Integer oldValue = mapProperty.get(propertyName) + 1;
			mapProperty.put(propertyName, oldValue);
		}
	}
}