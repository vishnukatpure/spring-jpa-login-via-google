package com.dev.oauth.seccurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Order(Ordered.LOWEST_PRECEDENCE)
@Component
public class SocialAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	// ~ Static fields/initializers
	// =====================================================================================

	/**
	 * The plaintext password used to perform
	 * {@link PasswordEncoder#isPasswordValid(String, String, Object)} on when the
	 * user is not found to avoid SEC-2056.
	 */
	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

	// ~ Instance fields
	// ================================================================================================

	private PasswordEncoder passwordEncoder;

	/**
	 * The password used to perform
	 * {@link PasswordEncoder#isPasswordValid(String, String, Object)} on when the
	 * user is not found to avoid SEC-2056. This is necessary, because some
	 * {@link PasswordEncoder} implementations will short circuit if the password is
	 * not in a valid format.
	 */
	private String userNotFoundEncodedPassword;

	@Autowired
	private UserDetailsService userDetailsService;

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
		Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
	}

	protected final UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser;

		try {
			loadedUser = this.getUserDetailsService().loadUserByUsername(username);
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

	/**
	 * Sets the PasswordEncoder instance to be used to encode and validate
	 * passwords. If not set, the password will be compared as plain text.
	 * <p>
	 * For systems which are already using salted password which are encoded with a
	 * previous release, the encoder should be of type
	 * {@code org.springframework.security.authentication.encoding.PasswordEncoder}.
	 * Otherwise, the recommended approach is to use
	 * {@code org.springframework.security.crypto.password.PasswordEncoder}.
	 *
	 * @param passwordEncoder must be an instance of one of the
	 *                        {@code PasswordEncoder} types.
	 */
	public void setPasswordEncoder(Object passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

		if (passwordEncoder instanceof PasswordEncoder) {
			setPasswordEncoder((PasswordEncoder) passwordEncoder);
			return;
		}

		if (passwordEncoder instanceof org.springframework.security.crypto.password.PasswordEncoder) {
			setPasswordEncoder(new PasswordEncoder() {
				@Override
				public String encode(CharSequence rawPassword) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public boolean matches(CharSequence rawPassword, String encodedPassword) {
					// TODO Auto-generated method stub
					return false;
				}
			});

			return;
		}

		throw new IllegalArgumentException("passwordEncoder must be a PasswordEncoder instance");
	}

	private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");

		this.userNotFoundEncodedPassword = passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	protected UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}
}
