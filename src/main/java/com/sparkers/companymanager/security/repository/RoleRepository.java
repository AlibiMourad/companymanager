package com.sparkers.companymanager.security.repository;

import com.sparkers.companymanager.security.models.Role;
import com.sparkers.companymanager.security.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
