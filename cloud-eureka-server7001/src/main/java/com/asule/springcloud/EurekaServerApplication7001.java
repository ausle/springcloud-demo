package com.asule.springcloud;

import com.netflix.eureka.EurekaServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * created by asule on 2020-04-28 21:20
 */
//开启eureka-server
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication7001 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication7001.class,args);
    }
}
