package com.dev.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebAppSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled  from users where username=?")
				.authoritiesByUsernameQuery(
						"SELECT username, a.authority as role FROM users INNER JOIN user_authorities a ON users.id = a.id WHERE users.username=?")
				.passwordEncoder(encoder());
	}

	final String STATIC_RESOURCES[] = { "/images/**", "/css/**" };
	final String NON_SECURITY_API[] = { "/login", "/oauth-login/google/**" };

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(STATIC_RESOURCES).permitAll();
		http.authorizeRequests().antMatchers(NON_SECURITY_API).permitAll();

		http.authorizeRequests().antMatchers("/**").hasAnyRole("ADMIN", "USER").and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll().and().logout()
				.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll().and().httpBasic();
		http.csrf().disable();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}