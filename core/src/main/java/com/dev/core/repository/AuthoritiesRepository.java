package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.core.model.Authorities;
import com.dev.core.model.User;

public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

	List<Authorities> findByUsername(User user);

}
