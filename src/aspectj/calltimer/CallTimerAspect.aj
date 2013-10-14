package aspectj.calltimer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import aspectj.annotation.Monitored;

public aspect CallTimerAspect {

	pointcut executing(Object target):
		call(@Monitored * *(..))
		&& target(target);

	before(Object target): executing(target){
		String varName = thisJoinPoint.getSignature().getName();
		this.saveCall(varName, target);
	}

	public int amountCall(Object target, String property) {
		int counter = 0;
		for (Object object : map.get(property)) {
			if(object.equals(target))
				counter++;
		}
		return counter;
	}
	
	// *************FIXTURE*************************
	protected Map<String, List<Object>> map = new HashMap<String, List<Object>>();

	/**
	 * Save calls methods of objects that have been made
	 */
	protected void saveCall(String property, Object target){
		if(!map.containsKey(property)){ //Create a new map
			List<Object> listObj = new Vector<Object>(); 
			listObj.add(target);
			map.put(property, listObj);
		}else{ //Saves the object associated with that property
			map.get(property).add(target);
		}
	}
}