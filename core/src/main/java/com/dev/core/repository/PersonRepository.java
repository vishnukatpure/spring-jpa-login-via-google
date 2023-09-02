package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dev.core.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstName(String firstName);
}
