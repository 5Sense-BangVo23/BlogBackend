package com.dev.myblog.service;

import com.dev.myblog.model.Account;

import java.util.List;

public interface AccountService {

    Account createAccount(Account account);
    Account findAccountByUsername(String username);

    Account findAccountByEmail(String email);

    Account findAccountByUsernameOrEmail(String username,String email);

    List<Account> getAccounts();
}
