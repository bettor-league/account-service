package com.bettorleague.account.domain;

import com.bettorleague.microservice.model.audit.AuditedEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "accounts")
public class Account extends AuditedEntity{

    @Id
    private String id;

    @Indexed(unique = true)
    private String userId;

    private String favoriteTeam;
}
