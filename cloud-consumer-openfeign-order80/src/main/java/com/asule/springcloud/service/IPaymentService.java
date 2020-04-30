package com.asule.springcloud.service;

import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by asule on 2020-04-29 21:08
 */
/*
    对服务的接口调用往往是多次，在Ribbon+RestTemplate请求方式封装处理之上，Feign又做了封装。

    使用feign完成对服务方接口的绑定。通过使用注解的方式来配置服务名，定义与服务接口相同的路径和方法名，来完成映射。
    在该服务名下寻找对应服务接口。

    feign本身也有负载均衡的功能，因为它也集成了Ribbon。(默认使用轮询的负载均衡算法)
*/

/*
    openfeign的超时设置
        Openfrign默认只等待1秒钟。需设置feign的超时时间。它的超时设置也有ribbon进行设置
*/
/*

    Openfeign的日志配置:
 */
@Component
@FeignClient(name = "cloud-provider-payment")
public interface IPaymentService {

    @PostMapping(value="/payment/create")
    ServerResponse createPayment(Payment payment);

    @GetMapping(value="/payment/get/{id}")
    ServerResponse<Payment> getPaymentById(@PathVariable("id") Long id);

    //模拟一个超时的请求，用于测试openfeign的超时控制.
    @GetMapping(value = "/payment/feign/timeout")
    String paymentOpenFeignTimeout();


}
