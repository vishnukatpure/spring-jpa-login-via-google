package com.dev.spring.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Table(name = "authorities")
@Entity
public class Authorities extends EntityBase implements GrantedAuthority {

	private static final long serialVersionUID = -3685793083409982451L;

	@ManyToOne
	private User username;

	private String authority;

	public User getUsername() {
		return username;
	}

	public void setUsername(User username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
