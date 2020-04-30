package com.asule.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * created by asule on 2020-04-30 16:36
 */

//@Component
@FeignClient(name = "cloud-provider-hystrix-payment")
public interface IHystrixPaymentService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfoByOK(@PathVariable("id") Integer id);


    @GetMapping("/payment/hystrix/timeout/{id}")
    String paymentInfoByTimeout(@PathVariable("id") Integer id);

}
