package com.dev.myblog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_accounts")
public class Account implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Added strategy for ID generation
    @Id
    private Long id;
    private String email;
    private String username;
    private String password;
    private boolean enabled = true;
    private boolean credentialExpired = false;
    private boolean expired = false;
    private boolean locked = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tbl_account_roles",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (roles == null || roles.isEmpty()) {
            throw new RuntimeException("Account has no roles assigned");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName())); // Assuming role.getCode() returns the role name
        }
        return authorities;
    }


    @Override
    public boolean isEnabled() {
        return enabled; // Make sure this returns true if the account is enabled
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired; // Return true if the account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked; // Return true if the account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialExpired; // Return true if the credentials are not expired
    }


    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", enabled=" + enabled +
                ", credentialExpired=" + credentialExpired +
                ", expired=" + expired +
                ", locked=" + locked +
                '}';
    }

}
