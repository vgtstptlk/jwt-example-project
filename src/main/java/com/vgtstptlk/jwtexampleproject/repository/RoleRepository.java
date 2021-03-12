package com.vgtstptlk.jwtexampleproject.repository;

import com.vgtstptlk.jwtexampleproject.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    public Optional<Role> findById(Long id);
    public Optional<Role> findByName(String name);
}
