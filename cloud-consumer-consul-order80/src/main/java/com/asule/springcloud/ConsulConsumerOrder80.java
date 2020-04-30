package com.asule.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * created by asule on 2020-04-29 16:53
 */
/*访问consul注册中心的微服务*/
@SpringBootApplication
public class ConsulConsumerOrder80 {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerOrder80.class,args);
    }
}
