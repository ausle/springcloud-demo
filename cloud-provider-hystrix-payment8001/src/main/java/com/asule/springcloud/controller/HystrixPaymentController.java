package com.asule.springcloud.controller;

import com.asule.springcloud.service.HystrixPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by asule on 2020-04-30 10:14
 */


@RestController
@Slf4j
public class HystrixPaymentController {

    @Autowired
    private HystrixPaymentService paymentService;

    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfoByOK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoByOK(id);
        log.info("*****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfoByTimeout(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfoByTimeout(id);
        log.info("*****result: " + result);
        return result;
    }

    @GetMapping("/payment/hystrix/circuitbreaker/{id}")
    public String paymentCircuitbreaker(@PathVariable("id") Integer id) {
        String result=paymentService.paymentCircuitBreaker(id);
        log.info("*****result: " + result);
        return result;
    }

}
