package aspectj.validator;

public class ValidateFirstCharIsUpperCase implements Validator<String>{

	@Override
	public boolean validate(String value) {
		return Character.isUpperCase(value.charAt(0));
	}

}
