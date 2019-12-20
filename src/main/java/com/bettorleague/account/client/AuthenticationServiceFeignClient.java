package com.bettorleague.account.client;

import com.bettorleague.account.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service")
public interface AuthenticationServiceFeignClient {
    @PostMapping(value = "/user")
    UserDto createUser(@RequestBody UserDto user);
}
