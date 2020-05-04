package com.bettorleague.account.repository;

import com.bettorleague.account.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {
    //Optional<Account> findByUsername(String username);
}