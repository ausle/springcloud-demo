package com.asule.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * created by asule on 2020-04-27 21:33
 */
@SpringBootApplication
@EnableEurekaClient
public class PaymentSpringApplication8802 {

    public static void main(String[] args) {
        SpringApplication.run(PaymentSpringApplication8802.class,args);
    }
}
