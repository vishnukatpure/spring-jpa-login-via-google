package com.dev.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.core.model.Authorities;
import com.dev.core.model.User;
import com.dev.core.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthoritiesService authoritiesService;

	@Transactional
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional
	public User getById(Long id) {
		return userRepository.findById(id).get();
	}

	@Transactional
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

	/**
	 * @CachePut update the cache entries whenever we alter them
	 * @param user
	 * @return
	 */
	@Transactional
	@CachePut(value = "users", key = "#result.id")
	public boolean updateUser(User user) {
		return userRepository.save(user) != null;
	}

	/**
	 * @Cacheable enable caching behavior for a method
	 * @param username
	 * @return
	 */
	@Cacheable(cacheNames = "users")
	public User findByUsername(String username) {
		List<User> users = userRepository.findByUsername(username);
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("User Not Found");
		}
		return users.get(0);
	}

	@Transactional
	public User addUser(String firstName, String lastName, String email, String sex, String password) {

		User user = new User();
		user.setCreateDate(LocalDateTime.now());
		user.setUpdatedDate(LocalDateTime.now());
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSex(sex);
		user.setPassword(password);

		return userRepository.save(user);
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public UserDetails loadUserByUsernameWithAuthorities(String username) {
		User user = findByUsername(username);
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
