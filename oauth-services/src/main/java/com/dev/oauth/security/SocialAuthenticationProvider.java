package com.dev.oauth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.dev.core.services.UserService;

@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class SocialAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

	private PasswordEncoder passwordEncoder;

	private String userNotFoundEncodedPassword;

	@Autowired
	private UserService userDetailsService;

	public SocialAuthenticationProvider() {
		setPasswordEncoder(new BCryptPasswordEncoder());
	}

	// ~ Methods
	// ========================================================================================================

	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		/**
		 * Add additional authentication checks
		 */
	}

	protected void doAfterPropertiesSet() {
		Assert.notNull(this.getUserDetailsService(), "A UserDetailsService must be set");
	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsernameWithAuthorities(username);
		} catch (UsernameNotFoundException notFound) {
			if (authentication.getCredentials() != null) {
				String presentedPassword = authentication.getCredentials().toString();
				passwordEncoder.matches(userNotFoundEncodedPassword, presentedPassword);
			}
			throw notFound;
		} catch (Exception repositoryProblem) {
			throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
		}

		if (loadedUser == null) {
			throw new InternalAuthenticationServiceException(
					"UserDetailsService returned null, which is an interface contract violation");
		}
		return loadedUser;
	}

	private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

		this.userNotFoundEncodedPassword = passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	protected UserService getUserDetailsService() {
		return userDetailsService;
	}
}
