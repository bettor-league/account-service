package com.bettorleague.account.query.api.rest;

import com.bettorleague.account.core.model.Account;
import com.bettorleague.account.core.model.AccountPage;
import com.bettorleague.account.core.query.FindAccountByIdentifier;
import com.bettorleague.account.core.query.FindAllAccountPaginated;
import com.bettorleague.microservice.cqrs.dispatacher.QueryDispatcher;
import com.bettorleague.microservice.cqrs.domain.QueryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/account")
public class AccountQueryController {

    private final QueryDispatcher queryDispatcher;

    @GetMapping
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AccountPage.class)))
    })
    public QueryResponse getAllAccount(@Valid @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                       @Valid @RequestParam(value = "size", required = false, defaultValue = "5") int size,
                                       @Valid @RequestParam(value = "sort", required = false, defaultValue = "pseudonym") String sort,
                                       @Valid @RequestParam(value = "sortDirection", required = false, defaultValue = "DESC") Sort.Direction sortDirection) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection.equals(Sort.Direction.ASC) ? Sort.Order.asc(sort) : Sort.Order.desc(sort)));
        final FindAllAccountPaginated findAllPaginatedQuery = new FindAllAccountPaginated(pageable);
        return queryDispatcher.send(findAllPaginatedQuery);
    }

    @GetMapping(path = "/{id}")
    @Operation(security = @SecurityRequirement(name = "OAuth2TokenBearer"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Account.class)))
    })
    public QueryResponse getAccountById(@PathVariable(value = "id") String id) {
        final FindAccountByIdentifier findByIdentifierQuery = new FindAccountByIdentifier(id);
        return queryDispatcher.send(findByIdentifierQuery);
    }
}
