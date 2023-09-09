package com.dev.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.util.StringUtils;

import com.dev.core.custom.exception.FormValidationException;

@Entity
@Table(name = "people")
@EnableJpaAuditing
public class Person extends EntityBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1676382221207795923L;
	@Column(name = "age")
	private Integer age;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person{" + "id=" + getId() + ", age=" + age + ", firstName='" + firstName + '\'' + ", lastName='"
				+ lastName + '\'' + '}';
	}

	public void validate() {
		String msg = "";
		if (!StringUtils.hasLength(firstName))
			msg += "invalid First name";

		if (StringUtils.hasLength(msg))
			throw new FormValidationException(msg);
	}
}
