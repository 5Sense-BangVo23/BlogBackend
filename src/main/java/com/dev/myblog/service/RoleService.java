package com.dev.myblog.service;

import com.dev.myblog.model.Role;

public interface RoleService {
    Role findRoleByName(String name);
    void save(Role role);
}
