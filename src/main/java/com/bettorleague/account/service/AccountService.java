package com.bettorleague.account.service;

import com.bettorleague.account.domain.Account;
import com.bettorleague.microservice.model.security.UserCreationRequest;

public interface AccountService {
    Account create(UserCreationRequest user);
    void delete(String accountId);
}
