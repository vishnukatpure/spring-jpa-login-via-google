package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.core.model.Authorities;
import com.dev.core.model.User;

@Repository
public interface AuthoritiesRepository extends CrudRepository<Authorities, Long> {

	List<Authorities> findByUsername(User user);

}
