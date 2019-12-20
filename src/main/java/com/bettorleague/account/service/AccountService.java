package com.bettorleague.account.service;

import com.bettorleague.account.dto.UserDto;

public interface AccountService {
    UserDto create(UserDto user);
}
