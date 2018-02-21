package com.walmart.ticketing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = "com.walmart.controller")
@EnableSwagger2
public class TicketingApplication {

  public static void main(final String[] args) {
    SpringApplication.run(TicketingApplication.class, args);
  }
}
