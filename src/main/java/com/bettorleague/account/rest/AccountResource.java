package com.bettorleague.account.rest;


import com.bettorleague.account.domain.Account;
import com.bettorleague.account.repository.AccountRepository;
import com.bettorleague.account.service.AccountService;
import com.bettorleague.microservice.model.exception.ResourceNotFoundException;
import com.bettorleague.microservice.model.security.UserCreationRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
@RestController
@RequestMapping("/account")
public class AccountResource {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    public AccountResource(AccountService accountService,
                           AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    @PostMapping
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    public Account createNewAccount(@Valid @RequestBody UserCreationRequest user) {
        return this.accountService.create(user);
    }

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    public Page<Account> getAllAccount(@Valid @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @Valid @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                       @Valid @RequestParam(value = "sort", required = false, defaultValue = "favoriteTeam") String sort,
                                       @Valid @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") Sort.Direction sortDirection) {
        return this.accountRepository.findAll(PageRequest.of(page, size, Sort.by(sortDirection.equals(Sort.Direction.ASC) ? Sort.Order.asc(sort) : Sort.Order.desc(sort))));
    }

    @GetMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    public Account getAccountById(@Valid @NotEmpty @PathVariable(value = "id") String id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Account", "id", id));
    }

    @DeleteMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    public void deleteAccountById(@Valid @NotEmpty @PathVariable(value = "id") String id) {
        accountService.delete(id);
    }


    @PatchMapping("/{id}")
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    public void updateAccountById(@Valid @NotEmpty @PathVariable(value = "id") String id, @RequestBody Account accountToUpdate) {
        accountRepository.findById(id).ifPresent(account -> {
            account.setFavoriteTeam("PSG");
            accountRepository.save(account);
        });
    }
}
