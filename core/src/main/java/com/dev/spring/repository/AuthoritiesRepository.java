package com.dev.spring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.spring.model.Authorities;
import com.dev.spring.model.User;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

	List<Authorities> findByUsername(User user);

}
