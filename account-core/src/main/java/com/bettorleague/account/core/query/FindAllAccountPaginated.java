package com.bettorleague.account.core.query;

import com.bettorleague.account.core.model.Account;
import com.bettorleague.microservice.model.query.FindAllPaginated;
import org.springframework.data.domain.Pageable;

public class FindAllAccountPaginated extends FindAllPaginated<Account> {
    public FindAllAccountPaginated(Pageable pageable) {
        super(pageable);
    }
}
