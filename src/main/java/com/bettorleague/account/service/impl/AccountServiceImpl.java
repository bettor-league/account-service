package com.bettorleague.account.service.impl;

import com.bettorleague.account.client.AuthenticationServiceFeignClient;
import com.bettorleague.account.dto.UserDto;
import com.bettorleague.account.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AuthenticationServiceFeignClient authenticationServiceFeignClient;

    public AccountServiceImpl(AuthenticationServiceFeignClient authenticationServiceFeignClient) {
        this.authenticationServiceFeignClient = authenticationServiceFeignClient;
    }

    @Override
    public UserDto create(UserDto user) {
        return authenticationServiceFeignClient.createUser(user);
    }
}
