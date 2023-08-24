package com.dev.oauth.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.core.model.Authorities;
import com.dev.core.model.User;
import com.dev.core.services.AuthoritiesService;
import com.dev.core.services.UserService;

@Service
public class CustomUserService implements UserDetailsService {

	@Autowired
	UserService userService;

	@Autowired
	AuthoritiesService authoritiesService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		user.setAuthorities(findGrantedAuthoritiesForUser(user));
		return user;
	}

	private List<GrantedAuthority> findGrantedAuthoritiesForUser(User user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<Authorities> authorities = authoritiesService.findByUserName(user);
		authorities.forEach(e -> grantedAuthorities.add(e));
		return grantedAuthorities;
	}

}
