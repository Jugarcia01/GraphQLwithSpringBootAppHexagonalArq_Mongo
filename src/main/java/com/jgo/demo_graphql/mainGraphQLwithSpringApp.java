package com.jgo.demo_graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.jgo.demo_graphql"})
public class mainGraphQLwithSpringApp {

    public static void main(String[] args) {
        SpringApplication.run(mainGraphQLwithSpringApp.class, args);
    }

}
