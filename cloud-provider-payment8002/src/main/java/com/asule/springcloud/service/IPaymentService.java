package com.asule.springcloud.service;


import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;

/**
 * created by asule on 2020-04-27 22:01
 */
public interface IPaymentService {

    ServerResponse createPayment(Payment payment);

    ServerResponse getPaymentById(Long id);
}
