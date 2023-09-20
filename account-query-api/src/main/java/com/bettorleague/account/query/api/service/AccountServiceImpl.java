package com.bettorleague.account.query.api.service;

import com.bettorleague.account.core.model.Account;
import com.bettorleague.account.core.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Account openAccount(String userId, String userEmail) {
        final Account account = Account.builder()
                .userId(userId)
                .userEmail(userEmail)
                .build();
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findByAccountId(String identifier) {
        return accountRepository.findById(identifier);
    }

    @Override
    public void closeAccount(String userId) {
        accountRepository.deleteAccountByUserId(userId);
    }
}
