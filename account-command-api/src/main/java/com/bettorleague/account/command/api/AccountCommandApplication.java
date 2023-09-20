package com.bettorleague.account.command.api;


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


@EventMicroservice
@MongoMicroservice
@CommonMicroservice
@SwaggerMicroservice
@SecuredMicroservice
@SpringBootApplication
@RequiredArgsConstructor
@OpenAPIDefinition(info = @Info(title = "Account command API", version = "1.0", description = "Documentation Account command API v1.0"))
public class AccountCommandApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountCommandApplication.class, args);
    }

}
