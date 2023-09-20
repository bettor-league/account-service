package com.bettorleague.account.core.model;

import com.bettorleague.microservice.model.doc.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class AccountPage extends Page<Account> {
    public AccountPage(List<Account> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }
}
