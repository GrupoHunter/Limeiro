package aspectj.validator;

public class ValidateNonEmptyString implements Validator<String>{

	@Override
	public boolean validate(String value) {
		return value.length() > 0;
	}

}
