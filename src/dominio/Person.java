package dominio;

import aspectj.annotation.Monitored;

public class Person {
	
	private String name;
	
	@Monitored
	public String getName() {
		return name;
	}

	@Monitored
	public void setName(String name) {
		this.name = name;
	}
}
