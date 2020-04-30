package com.asule.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * created by asule on 2020-04-30 21:01
 */
@Component
public class HystrixPaymentServiceImpl implements IHystrixPaymentService{

    //经过测试，可以覆盖全局的fallback
    //服务发生异常、超时、或者宕机都会进行服务降级。
    @Override
    public String paymentInfoByOK(Integer id) {
        String fallbackInfo="paymentInfoByOK-fallback,"+id+"="+id;
        return fallbackInfo;
    }

    @Override
    public String paymentInfoByTimeout(Integer id) {
        String fallbackInfo="paymentInfoByTimeout-fallback,"+id+"="+id;
        return fallbackInfo;
    }
}
