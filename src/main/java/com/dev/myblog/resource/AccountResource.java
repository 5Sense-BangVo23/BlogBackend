package com.dev.myblog.resource;


import com.dev.myblog.model.Account;
import com.dev.myblog.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/admin/accounts")
@RequiredArgsConstructor
public class AccountResource {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account createdAccount;

        try {
            createdAccount = accountService.createAccount(account);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        URI location = getLocation(createdAccount.getId());

        return ResponseEntity.created(location).body(createdAccount);
    }


    @GetMapping
    public ResponseEntity<List<Account>> getAccounts(){
        return ResponseEntity.ok(accountService.getAccounts());

    }

    private URI getLocation(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

}
