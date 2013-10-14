package aspectj.validator;

public interface Validator<T> {
	public boolean validate (T value);
}
