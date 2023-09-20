package com.bettorleague.account.query.api.service;

import com.bettorleague.account.core.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AccountService {

    Page<Account> findAll(Pageable pageable);

    Account openAccount(String userId, String userEmail);

    Optional<Account> findByAccountId(String identifier);

    void closeAccount(String userId);
}
