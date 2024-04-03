package com.dev.web.config;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebAppSecurity {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
		authenticationMgr.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username, password, enabled  from users where username=?")
				.authoritiesByUsernameQuery(
						"SELECT username, r.role FROM users INNER JOIN user_authorities a ON users.id = a.id INNER JOIN roles r ON a.id=r.id where users.username=?")
				.passwordEncoder(encoder());
	}

	@Bean
	public Filter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	final String STATIC_RESOURCES[] = { "/images/**", "/css/**" };
	final String NON_SECURITY_API[] = { "/login", "/oauth-login/google/**" };

	@SuppressWarnings("deprecation")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests().antMatchers(STATIC_RESOURCES).permitAll();
		http.authorizeHttpRequests().antMatchers(NON_SECURITY_API).permitAll();

		http.authorizeHttpRequests().antMatchers("/**").hasAnyRole("ADMIN", "USER").anyRequest().authenticated();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/home").failureUrl("/login?error=true").permitAll()
				.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll().and()
				.httpBasic();
		http.csrf().disable();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}