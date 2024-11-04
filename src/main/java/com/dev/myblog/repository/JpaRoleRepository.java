package com.dev.myblog.repository;

import com.dev.myblog.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String name);
}
