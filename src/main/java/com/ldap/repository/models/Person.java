package com.ldap.repository.models;

import javax.naming.Name;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;


@Entry(base = "ou=people", objectClasses = { "person", "inetOrgPerson", "top", "organizationalPerson" })
public class Person {
	@Id
	private Name id;

	@Attribute(name = "sn")
	private String surname;

	@Attribute(name = "uid")
	private String email;

	@Attribute(name = "cn")
	private String name;
	
	@Attribute(name = "userPassword")
	private String userPassword;

	public Person() {
	}

	public Person(String surname, String email, String name, String userPassword) {
		super();
		this.surname = surname;
		this.email = email;
		this.name = name;
		this.userPassword = userPassword;
	}

	public Name getId() {
		return id;
	}

	public void setId(Name id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	@Override
	public String toString() {
		return "Person{" + "fullName='" + email + '\'' + ", lastName='" + name + '\'' + '}';
	}
}
