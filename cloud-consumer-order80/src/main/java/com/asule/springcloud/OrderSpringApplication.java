package com.asule.springcloud;

import com.asule.rule.MyRuleConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * created by asule on 2020-04-27 21:33
 */
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(configuration = MyRuleConfig.class,name = "CLOUD-PROVIDER-PAYMENT")
public class OrderSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSpringApplication.class,args);
    }
}
