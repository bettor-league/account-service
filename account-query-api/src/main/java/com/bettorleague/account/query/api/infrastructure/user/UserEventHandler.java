package com.bettorleague.account.query.api.infrastructure.user;

import com.bettorleague.account.query.api.service.AccountService;
import com.bettorleague.authentication.core.event.UserRegistered;
import com.bettorleague.authentication.core.event.UserRemoved;
import com.bettorleague.microservice.cqrs.annotations.HandleEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventHandler {
    private final AccountService accountService;
    @HandleEvent
    public void handle(UserRegistered event) {
        accountService.openAccount(event.getAggregateIdentifier(), event.getEmail());
    }

    @HandleEvent
    public void handle(UserRemoved event) {
        accountService.closeAccount(event.getAggregateIdentifier());
    }
}
