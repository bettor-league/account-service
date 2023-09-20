package com.bettorleague.account.query.api;

import com.bettorleague.microservice.common.CommonMicroservice;
import com.bettorleague.microservice.cqrs.EventMicroservice;
import com.bettorleague.microservice.mongo.MongoMicroservice;
import com.bettorleague.microservice.security.SecuredMicroservice;
import com.bettorleague.microservice.swagger.SwaggerMicroservice;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
@EventMicroservice
@MongoMicroservice
@CommonMicroservice
@SwaggerMicroservice
@SecuredMicroservice
@SpringBootApplication
@RequiredArgsConstructor
@EnableMongoRepositories(basePackages = "com.bettorleague.account.core.repository")
@OpenAPIDefinition(info = @Info(title = "Account query API", version = "1.0", description = "Documentation Account query API v1.0"))
public class AccountQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountQueryApplication.class, args);
    }


}
