package com.asule.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * created by asule on 2020-04-29 16:51
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulPaymentApplication8006 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulPaymentApplication8006.class,args);
    }


}
