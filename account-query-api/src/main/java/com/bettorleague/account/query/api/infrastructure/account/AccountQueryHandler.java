package com.bettorleague.account.query.api.infrastructure.account;

import com.bettorleague.account.core.model.Account;
import com.bettorleague.account.core.query.FindAccountByIdentifier;
import com.bettorleague.account.core.query.FindAllAccountPaginated;
import com.bettorleague.account.query.api.service.AccountService;
import com.bettorleague.microservice.cqrs.annotations.HandleQuery;
import com.bettorleague.microservice.cqrs.domain.QueryResponse;
import com.bettorleague.microservice.model.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountQueryHandler {

    private final AccountService accountService;

    @HandleQuery
    public QueryResponse handle(FindAllAccountPaginated query) {
        final Page<Account> acccountPage = accountService.findAll(query.getPageable());
        return new QueryResponse(acccountPage);
    }


    @HandleQuery
    public QueryResponse handle(FindAccountByIdentifier query) {
        final String accountIdentifier = query.getIdentifier();
        final Account account = accountService.findByAccountId(accountIdentifier)
                .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountIdentifier));
        return new QueryResponse(account);
    }
}
