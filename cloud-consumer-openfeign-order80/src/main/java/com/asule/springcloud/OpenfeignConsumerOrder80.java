package com.asule.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * created by asule on 2020-04-29 21:06
 */

/*使得FeignClient可用*/
@EnableFeignClients
@SpringBootApplication
@EnableHystrix
public class OpenfeignConsumerOrder80 {

    public static void main(String[] args) {
        SpringApplication.run(OpenfeignConsumerOrder80.class,args);
    }

}
