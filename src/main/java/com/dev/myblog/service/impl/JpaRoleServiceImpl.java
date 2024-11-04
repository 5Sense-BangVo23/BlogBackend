package com.dev.myblog.service.impl;


import com.dev.myblog.model.Role;
import com.dev.myblog.repository.JpaRoleRepository;
import com.dev.myblog.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JpaRoleServiceImpl implements RoleService {

    private final JpaRoleRepository jpaRoleRepository;
    @Override
    public Role findRoleByName(String name) {
        return jpaRoleRepository.findRoleByName(name);
    }

    @Override
    public void save(Role role) {
        jpaRoleRepository.save(role);
    }
}
