package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.core.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByUsername(String username);
}
