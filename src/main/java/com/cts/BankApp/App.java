package com.cts.BankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hello world!
 */
@Configuration
//@EntityScan
@EntityScan(basePackages = {"com.cts"})
@EnableJpaRepositories(basePackages = {"com.cts.repository"})
@ComponentScan(basePackages = {"com.cts.controller", "com.cts.repository", "com.cts.users"})
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class,args);
    }
}
