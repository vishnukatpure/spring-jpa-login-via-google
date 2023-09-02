package com.dev.core.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.core.model.Roles;
import com.dev.core.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	RolesRepository rolesRepository;

	@Transactional
	public List<Roles> getAllAuthoritiess() {
		return (List<Roles>) rolesRepository.findAll();
	}

	@Transactional
	public Roles getById(Long id) {
		return rolesRepository.findById(id).get();
	}

	@Transactional
	public void deleteAuthorities(Long authoritiesId) {
		rolesRepository.deleteById(authoritiesId);
	}

	@Transactional
	public boolean addAuthorities(Roles roles) {
		return rolesRepository.save(roles) != null;
	}

	@Transactional
	public boolean updateAuthorities(Roles roles) {
		return rolesRepository.save(roles) != null;
	}

	@Transactional
	public Roles findByRole(String role) {
		List<Roles> roles = rolesRepository.findByRole(role);
		return roles.isEmpty() ? null : roles.get(0);
	}
}
