package com.dev.core.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.GrantedAuthority;

@Table(name = "user_authorities")
@Entity
@EnableJpaAuditing
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
