package com.api.enchentes.user.repository;

import com.api.enchentes.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRolename(String rolename);
}