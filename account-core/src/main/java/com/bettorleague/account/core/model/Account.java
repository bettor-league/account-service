package com.bettorleague.account.core.model;

import com.bettorleague.microservice.mongo.config.AuditedEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class Account extends AuditedEntity {
    @Id
    private String id;
    @Indexed(unique = true)
    private String userId;
    @Indexed(unique = true)
    private String userEmail;
    private String pseudonym;
    private String firstName;
    private String lastName;
    private String favoriteTeam;
}
