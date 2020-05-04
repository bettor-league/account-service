package com.bettorleague.account.rest;


import com.bettorleague.account.dto.UserDto;
import com.bettorleague.account.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountResource {

    private final AccountService accountService;

    public AccountResource(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public UserDto createNewAccount(@RequestBody UserDto user) {
        return this.accountService.create(user);
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(security = @SecurityRequirement(name = "OAuth2PasswordBearer"))
    public String secured() {
        return "coucou";
    }
}
