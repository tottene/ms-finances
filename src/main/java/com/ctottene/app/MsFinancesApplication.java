package com.ctottene.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ctottene")
@EnableJpaRepositories(basePackages = "com.ctottene.infrastructure.persistence")
@EntityScan(basePackages = "com.ctottene.infrastructure.persistence.entity")
public class MsFinancesApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsFinancesApplication.class, args);
    }
}