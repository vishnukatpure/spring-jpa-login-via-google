package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.core.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	public List<User> findByUsername(String username);
}
