package com.acme.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//Entity
@XmlRootElement(name="person")
public class Person {

	@Id Long id;
	String name;
	
	public Person() {}

	public Person(String name) {
		this.name = name;
	}

	@XmlElement	
	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	@XmlElement	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
