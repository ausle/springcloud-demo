package com.asule.rule;

import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * created by asule on 2020-04-29 20:05
 */
@Configuration
public class MyRuleConfig {


    /*

        负载均衡算法：
            轮询负载算法：接口请求的次数和服务的数量取余，获得要请求的服务下标，获取请求的服务。当每次服务重启后，请求的次数再从1开始。

        todo
            会手写一个轮询的负载算法，用到的只是有：CAS+自旋锁。(暂时不懂，跳过)

    */

    /*定义随机负载策略*/
    @Bean
    public RandomRule rule(){
        RandomRule randomRule = new RandomRule();
        return randomRule;
    }






}
