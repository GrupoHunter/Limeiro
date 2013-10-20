package aspectj.validator;

public class ValidateStringContainsA implements Validator<String> {

	@Override
	public boolean validate(String value) {
		return value.contains("a");
	}

}
