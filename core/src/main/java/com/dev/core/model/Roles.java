package com.dev.core.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Table(name = "roles")
@Entity
@EnableJpaAuditing
public class Roles extends EntityBase {

	private static final long serialVersionUID = 2213248833866696724L;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
