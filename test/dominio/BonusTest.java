package dominio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import aspectj.bonus.calltimer.CallTimerPerAspect;
import aspectj.validator.InvalidValueException;
import aspectj.validator.ValidateNonEmptyString;

public class BonusTest {

	@Test
	public void bonusCallTimer() {
		Person person = new Person();
		person.getName();
		person.setName("Jona");
		person.setName("Damian");

		Person person2 = new Person();
//		person2.getName(); 

		int call_person_getName = CallTimerPerAspect.aspectOf(person).amountCall("getName");
		int call_person_setName = CallTimerPerAspect.aspectOf(person).amountCall("setName");
		int call_person2_SetName = CallTimerPerAspect.aspectOf(person2).amountCall("setName");
		
		assertEquals(1, call_person_getName);
		assertEquals(2, call_person_setName);
		assertEquals(0, call_person2_SetName);

	}
	
//	@Test
//	public void bonusShouldNotAllowEmptyNames() {
//		Person p1 = new Person();
//		
//		p1.addValidator("name", new ValidateNonEmptyString());
//		
//		p1.setName("Jona");
//		assertEquals("Jona", p1.getName());
//		
//		try {
//			p1.setName("");
//			fail("Should have thrown an exception when assign an empty name");
//		} catch (InvalidValueException e) {
//			assertEquals("Jona", p1.getName()); //The name didn't change
//		}
//		
//	}

}
