package com.dev.myblog.security;

import com.dev.myblog.model.Account;
import com.dev.myblog.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Try to find the account by username first
        Account account = accountService.findAccountByUsername(usernameOrEmail);
        System.out.println("");
        // If not found, try to find it by email
        if (account == null) {
            account = accountService.findAccountByEmail(usernameOrEmail);
        }

        // Throw an exception if the account was not found
        if (account == null) {
            throw new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail);
        }

        log.info("Account found: {}, Roles: {}", account.getUsername(), account.getRoles());

        // Get authorities (roles) for the account
        Collection<GrantedAuthority> authorities = getAuthorities(account);

        return new org.springframework.security.core.userdetails.User(
                account.getUsername(),
                account.getPassword(),
                account.isEnabled(),
                !account.isExpired(),
                account.isCredentialsNonExpired(),
                !account.isLocked(),
                authorities
        );
    }

    private Collection<GrantedAuthority> getAuthorities(Account account) {
        // Map roles to GrantedAuthorities with the "ROLE_" prefix
        return account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toList());
    }
}
