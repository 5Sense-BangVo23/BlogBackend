package com.dev.myblog;

import com.dev.myblog.model.Account;
import com.dev.myblog.model.Role;
import com.dev.myblog.service.AccountService;
import com.dev.myblog.service.RoleService; // Assuming you have a RoleService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService; // Assuming you have a service for Role management

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = roleService.findRoleByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleService.save(adminRole);
        }

        Role userRole = roleService.findRoleByName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleService.save(userRole);
        }

        if (accountService.findAccountByUsername("admin") == null &&
                accountService.findAccountByEmail("admin@example.com") == null) {

            Account adminAccount = new Account();
            adminAccount.setUsername("admin");
            adminAccount.setEmail("admin@example.com");
            adminAccount.setPassword(passwordEncoder.encode("admin"));
            adminAccount.setRoles(new HashSet<>(Set.of(adminRole)));

            accountService.createAccount(adminAccount);
        }
    }
}
