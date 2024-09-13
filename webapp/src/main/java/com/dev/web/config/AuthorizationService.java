package com.dev.web.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

	public boolean hasRole(String obj) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities();
		boolean flag = authorities.stream().filter(e -> e.getAuthority().equalsIgnoreCase(obj)).collect(Collectors.toList()).size() >= 1;
		return flag;
	}
}
