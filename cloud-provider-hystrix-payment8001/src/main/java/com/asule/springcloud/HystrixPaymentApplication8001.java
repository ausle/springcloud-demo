package com.asule.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

/**
 * created by asule on 2020-04-30 10:11
 */

//开启circuit-breaker
@EnableCircuitBreaker
@SpringBootApplication
public class HystrixPaymentApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(HystrixPaymentApplication8001.class,args);
    }

}
