package com.dev.core.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dev.core.model.Roles;

public interface RolesRepository extends CrudRepository<Roles, Long> {

	List<Roles> findByRole(String role);
}
