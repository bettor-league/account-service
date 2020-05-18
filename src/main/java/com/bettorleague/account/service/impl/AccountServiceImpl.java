package com.bettorleague.account.service.impl;

import com.bettorleague.account.client.AuthenticationServiceFeignClient;
import com.bettorleague.account.domain.Account;
import com.bettorleague.account.repository.AccountRepository;
import com.bettorleague.account.service.AccountService;
import com.bettorleague.microservice.model.exception.ResourceNotFoundException;
import com.bettorleague.microservice.model.security.UserCreationRequest;
import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AuthenticationServiceFeignClient authenticationServiceFeignClient;
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AuthenticationServiceFeignClient authenticationServiceFeignClient,
                              AccountRepository accountRepository) {
        this.authenticationServiceFeignClient = authenticationServiceFeignClient;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account create(UserCreationRequest userCreationRequest) {

        log.info("New account creation attempt: {} {} {}", userCreationRequest.getEmail(), userCreationRequest.getUsername(), userCreationRequest.getPassword() );

        String userId;
        JsonNode user;

        user = authenticationServiceFeignClient.createUser(userCreationRequest);
        userId = user.get("id").asText();

        if(StringUtils.isEmpty(userId)){
            log.error("Account creation failed cant fetch userId : : {}", user.toString());
            throw new RuntimeException("Account creation failed cant fetch userId : " + user.toString());
        }

        accountRepository.findByUserId(userId).ifPresent(accountRepository::delete);

        Account account = accountRepository.save(Account.builder().userId(userId).favoriteTeam("empty").build());

        log.info("New account has been created: {}", account.getUserId());

        return account;
    }

    @Override
    public  void delete(String accountId){
        log.info("Account {} deletion attempt", accountId );

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

        try {
            authenticationServiceFeignClient.deleteUser(account.getUserId());
        }catch (FeignException feignException){
            log.warn("Linked user {} not found, account deletion processing...",account.getUserId());
        }finally {
            accountRepository.delete(account);
        }

        log.info("Account {} has been deleted successfully", account.getId());

    }
}
