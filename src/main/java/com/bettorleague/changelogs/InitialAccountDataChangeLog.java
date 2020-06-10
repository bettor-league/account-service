package com.bettorleague.changelogs;

import com.bettorleague.account.domain.Account;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.util.Optional;

@ChangeLog
public class InitialAccountDataChangeLog {


    @ChangeSet(order = "001", id = "insertAdminAccount", author = "Nadjim Chabane")
    public void initAdminAccount(MongoTemplate mongoTemplate,
                                 Environment environment) {
        Instant now = Instant.now();

        Account adminAccount = Account.builder()
                .favoriteTeam("PSG")
                .userId(Optional.ofNullable(environment.getProperty("bettorleague.admin.id")).orElse("000000000000000000000001")).build();

        adminAccount.setCreatedAt(now);
        adminAccount.setLastModified(now);

        mongoTemplate.save(adminAccount);
    }

}
