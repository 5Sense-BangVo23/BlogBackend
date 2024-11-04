package com.dev.myblog.service.impl;

import com.dev.myblog.model.Account;
import com.dev.myblog.model.Role;
import com.dev.myblog.repository.JpaAccountRepository;
import com.dev.myblog.repository.JpaRoleRepository;
import com.dev.myblog.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class JpaAccountServiceImpl implements AccountService {

    private final JpaAccountRepository jpaAccountRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    @Override
    public Account createAccount(Account account) {
        Account existingAccount = jpaAccountRepository.findAccountByUsername(account.getUsername());

        if (existingAccount != null) {
            return existingAccount;
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Role role = jpaRoleRepository.findRoleByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        account.setRoles(roles);

        return jpaAccountRepository.save(account);
    }



    @Override
    public Account findAccountByUsername(String username) {
        return jpaAccountRepository.findAccountByUsername(username);
    }

    @Override
    public Account findAccountByEmail(String email) {
        return jpaAccountRepository.findAccountByEmail(email);
    }

    @Override
    public Account findAccountByUsernameOrEmail(String username, String email) {
        return jpaAccountRepository.findAccountByUsernameOrEmail(username,email);
    }

    @Override
    public List<Account> getAccounts() {
        return jpaAccountRepository.findAll();
    }
}
