package dominio;

import static org.junit.Assert.*;

import org.junit.Test;

import aspectj.calltimer.CallTimerAspect;

public class CallTimerTest {

	@Test
	public void sampleCallTimer() {
		Person person = new Person();
		person.getName();
		person.setName("Jona");
		person.setName("Damian");

		Person person2 = new Person();

		int call_person_getName = CallTimerAspect.aspectOf().amountCall(person, "getName");
		int call_person_setName = CallTimerAspect.aspectOf().amountCall(person,	"setName");
		int call_person2_SetName = CallTimerAspect.aspectOf().amountCall(person2, "setName");
		
		assertEquals(1, call_person_getName);
		assertEquals(2, call_person_setName);
		assertEquals(0, call_person2_SetName);

	}

}
