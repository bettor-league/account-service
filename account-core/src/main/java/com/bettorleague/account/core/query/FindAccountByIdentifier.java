package com.bettorleague.account.core.query;

import com.bettorleague.account.core.model.Account;
import com.bettorleague.microservice.model.query.FindByIdentifier;

public class FindAccountByIdentifier extends FindByIdentifier<Account> {
    public FindAccountByIdentifier(String identifier) {
        super(identifier);
    }
}
