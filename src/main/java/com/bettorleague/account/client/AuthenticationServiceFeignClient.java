package com.bettorleague.account.client;

import com.bettorleague.microservice.model.security.UserCreationRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authentication-service")
public interface AuthenticationServiceFeignClient {
    @PostMapping(value = "/user")
    JsonNode createUser(@RequestBody UserCreationRequest user);

    @DeleteMapping(value = "/user/{id}")
    void deleteUser(@PathVariable(value = "id") String id);

    @DeleteMapping(value = "/user/{id}")
    JsonNode getUser(@PathVariable(value = "id") String id);
}
