package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import aspectj.validator.InvalidValueException;
import aspectj.validator.ValidateFirstCharIsUpperCase;
import aspectj.validator.ValidateNonEmptyString;
import aspectj.validator.ValidateStringContainsA;
import aspectj.validator.ValidatorAspect;

public class NameNotEmpty {

	@Test
	public void shouldAllowEmptyNames() {
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		
		ValidatorAspect.aspectOf().addValidator(p1, "name", new ValidateNonEmptyString());
		ValidatorAspect.aspectOf().addValidator(p2, "name", new ValidateNonEmptyString());
		ValidatorAspect.aspectOf().addValidator(p3, "name", new ValidateNonEmptyString());
		
		p1.setName("Jona");
		assertEquals("Jona", p1.getName());
		
		p2.setName("Damian");
		assertEquals("Damian", p2.getName());
		
		p3.setName("Ging");
		assertEquals("Ging", p3.getName());
		
		try {
			p1.setName("");
			p2.setName("");
			p3.setName("");
			fail("Should have thrown an exception when assign an empty name");
		} catch (InvalidValueException e) {
			assertEquals("Jona", p1.getName()); //The name didn't change
			assertEquals("Damian", p2.getName());
			assertEquals("Ging", p3.getName());
		}
	}
	
	@Test
	public void withDifferentValidators(){
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		
		ValidatorAspect.aspectOf().addValidator(p1, "name", new ValidateNonEmptyString());
		
		ValidatorAspect.aspectOf().addValidator(p2, "name", new ValidateFirstCharIsUpperCase());
		
		ValidatorAspect.aspectOf().addValidator(p3, "name",new ValidateStringContainsA());
		
		p1.setName("Ging");
		assertEquals("Ging", p1.getName());
		
		p2.setName("Damian");
		assertEquals("Damian", p2.getName());
		
		p3.setName("Jona");
		assertEquals("Jona", p3.getName());
		
		try {
			p1.setName("");			
			fail("Should have thrown an exception when assign an empty name");
		} catch (InvalidValueException e) {
			assertEquals("Ging", p1.getName());
		}
		
		try {
			p2.setName("killua");
			fail("should have thrown an exception if first char is upper case");
		} catch (InvalidValueException e) {
			assertEquals("Damian", p2.getName());
		}
		
		try {
			p3.setName("Gong");
			fail("should have thrown an exception when string not contains the char <a> ");
		} catch (InvalidValueException e) {
			assertEquals("Jona", p3.getName());
		}
	}

}
