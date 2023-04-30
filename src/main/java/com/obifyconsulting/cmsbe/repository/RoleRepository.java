package com.obifyconsulting.cmsbe.repository;

import com.obifyconsulting.cmsbe.entity.ERole;
import com.obifyconsulting.cmsbe.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
