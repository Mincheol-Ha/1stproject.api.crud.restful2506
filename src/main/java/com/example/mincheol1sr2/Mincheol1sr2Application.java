package com.example.mincheol1sr2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Mincheol1sr2Application {

    public static void main(String[] args) {
        SpringApplication.run(Mincheol1sr2Application.class, args);
    }

}
