package com.bettorleague.account.repository;

import com.bettorleague.account.domain.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, String> {
    Page<Account> findAll(Pageable pageable);
    Optional<Account> findByUserId(String userId);
}