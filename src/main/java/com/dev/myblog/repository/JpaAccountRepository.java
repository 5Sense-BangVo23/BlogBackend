package com.dev.myblog.repository;

import com.dev.myblog.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account,Long> {
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String email);

    Account findAccountByUsernameOrEmail(String username, String email);
}
