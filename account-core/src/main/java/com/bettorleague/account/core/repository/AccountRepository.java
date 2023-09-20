package com.bettorleague.account.core.repository;

import com.bettorleague.account.core.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    Page<Account> findAll(Pageable pageable);
    Optional<Account> findAccountByUserEmail(String userEmail);
    Optional<Account> findAccountByUserId(String userId);
    void deleteAccountByUserId(String userId);
}