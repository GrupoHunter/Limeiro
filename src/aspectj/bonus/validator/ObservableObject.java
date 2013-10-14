package aspectj.bonus.validator;

import java.beans.PropertyChangeListener;

import aspectj.validator.Validator;

public interface ObservableObject {
	
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener);

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener);

	public void addValidator(String fieldName, Validator<?> validator);
}
