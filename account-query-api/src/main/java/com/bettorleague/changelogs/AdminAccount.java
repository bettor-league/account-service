package com.bettorleague.changelogs;

import com.bettorleague.account.core.model.Account;
import io.mongock.api.annotations.ChangeUnit;
import io.mongock.api.annotations.Execution;
import io.mongock.api.annotations.RollbackExecution;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@RequiredArgsConstructor
@ChangeUnit(id="admin-account", order = "001", author = AdminAccount.AUTHOR)
public class AdminAccount {
    public static final String AUTHOR = "Nadjim Chabane";
    public static final String ADMIN_USER_ID = "bettor-league.user.admin.id";
    public static final String ADMIN_USER_EMAIL = "bettor-league.user.admin.email";
    private final MongoTemplate mongoTemplate;
    private final Environment environment;
    @Execution
    public void insertAdminAccount() {
        final Instant now = Instant.now();
        final String userId = ofNullable(environment.getProperty(ADMIN_USER_ID))
                .orElseThrow();
        final String userEmail = ofNullable(environment.getProperty(ADMIN_USER_EMAIL))
                .orElseThrow();
        final String id = UUID.randomUUID().toString();

        final Account adminAccount = Account.builder()
                .id(id)
                .userId(userId)
                .userEmail(userEmail)
                .build();

        adminAccount.setCreatedAt(now);
        adminAccount.setLastModified(now);

        mongoTemplate.save(adminAccount);
    }

    @RollbackExecution
    public void rollback() {
        mongoTemplate.dropCollection("accounts");
    }

}
