package com.bettorleague.account.domain;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Document(collection = "accounts")
public class Account {

}
