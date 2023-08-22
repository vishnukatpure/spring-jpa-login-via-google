package com.dev.oauth.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.core.model.Authorities;
import com.dev.core.model.User;
import com.dev.core.services.AuthoritiesService;
import com.dev.core.services.UserService;
import com.dev.oauth.model.SocialUser;
import com.dev.oauth.repository.SocialUserRepository;
import com.dev.oauth.security.SocialAuthenticationProvider;

@Service
public class SocialUserService {

	@Autowired
	SocialUserRepository socialUserRepository;

	@Autowired
	SocialAuthenticationProvider socialAuthenticationProvider;

	@Autowired
	private AuthoritiesService authoritiesService;

	@Autowired
	UserService userService;

	@Transactional
	public List<SocialUser> getAllSocialUser() {
		return (List<SocialUser>) socialUserRepository.findAll();
	}

	@Transactional
	public SocialUser getById(Long id) {
		return socialUserRepository.findOne(id);
	}

	@Transactional
	public void deleteSocialUser(Long id) {
		socialUserRepository.delete(id);
	}

	@Transactional
	public boolean addSocialUser(SocialUser socialUser) {
		return socialUserRepository.save(socialUser) != null;
	}

	@Transactional
	public boolean updateSocialUser(SocialUser socialUser) {
		return socialUserRepository.save(socialUser) != null;
	}

	@Transactional
	private List<SocialUser> findByuserSocialIdOrEmail(String userSocialId, String email) {
		return socialUserRepository.findByuserSocialIdOrEmail(userSocialId, email);
	}

	public SocialUser saveUserAndSocialUser(SocialUser socialUser) {
		List<SocialUser> userSocialAuthDetailDB = findByuserSocialIdOrEmail(socialUser.getUserSocialId(),
				socialUser.getEmail());
		if (!userSocialAuthDetailDB.isEmpty()) {
			socialUser = userSocialAuthDetailDB.get(0);
		} else {
			User user = new User();
			user.setCreateDate(new Date());
			user.setEmail(socialUser.getEmail());
			user.setEnabled(true);
			user.setFirstName(socialUser.getFirstName());
			user.setLastName(socialUser.getLastName());
			user.setUsername(socialUser.getFirstName() + socialUser.getLastName());
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			// user.setSex(null);
			user = userService.saveUser(user);

			Authorities authorities = new Authorities();
			authorities.setAuthority("USER");
			authorities.setUsername(user);
			authorities.setCreateDate(new Date());
			authoritiesService.addAuthorities(authorities);
			socialUser.setUser(user);
			socialUser = socialUserRepository.save(socialUser);
		}
		return socialUser;
	}

	public void authenticateUser(User user) {
		List<GrantedAuthority> authorities = findGrantedAuthoritiesForUser(user);
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
		Authentication authenticated = socialAuthenticationProvider.authenticate(authentication);
		SecurityContextHolder.getContext().setAuthentication(authenticated);
	}

	private List<GrantedAuthority> findGrantedAuthoritiesForUser(User user) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		List<Authorities> authorities = authoritiesService.findByUserName(user);
		authorities.forEach(e -> grantedAuthorities.add(e));
		return grantedAuthorities;
	}

}
