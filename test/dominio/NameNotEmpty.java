package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import aspectj.validator.InvalidValueException;
import aspectj.validator.ValidateNonEmptyString;
import aspectj.validator.ValidatorAspect;

public class NameNotEmpty {

	@Test
	public void shouldAllowEmptyNames() {
		Person p1 = new Person();
		
		ValidatorAspect.aspectOf().addValidator(p1, "name", new ValidateNonEmptyString());
		
		p1.setName("Jona");
		assertEquals("Jona", p1.getName());
		
		try {
			p1.setName("");
			fail("Should have thrown an exception when assign an empty name");
		} catch (InvalidValueException e) {
			assertEquals("Jona", p1.getName()); //The name didn't change
		}
		
	}

}
