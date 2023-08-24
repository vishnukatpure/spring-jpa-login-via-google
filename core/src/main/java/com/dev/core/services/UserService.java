package com.dev.core.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.core.model.User;
import com.dev.core.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Transactional
	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional
	public List<User> findByName(String name) {
		return userRepository.findByFirstName(name);
	}

	@Transactional
	public User getById(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional
	public void deleteUser(Long userId) {
		userRepository.delete(userId);
	}

	@Transactional
	public boolean updateUser(User user) {
		return userRepository.save(user) != null;
	}

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
		user.setCreateDate(new Date());
		user.setUpdatedDate(new Date());
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

}
