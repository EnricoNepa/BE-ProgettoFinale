package com.epicenergy.security.repository;

import java.util.Optional;

import com.epicenergy.security.model.Role;
import com.epicenergy.security.model.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByRoleName(Roles role);
}
