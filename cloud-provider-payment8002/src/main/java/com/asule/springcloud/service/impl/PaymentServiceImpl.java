package com.asule.springcloud.service.impl;


import com.asule.springcloud.dao.PaymentMapper;
import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import com.asule.springcloud.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * created by asule on 2020-04-27 22:01
 */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Value("${server.port}")
    private String serverPort;

    @Override
    public ServerResponse createPayment(Payment payment) {
        int insert = paymentMapper.insert(payment);
        if (insert>0)
            return ServerResponse.createSuccessData("add success by"+serverPort,payment);

        return ServerResponse.createError("添加失败");
    }

    @Override
    public ServerResponse getPaymentById(Long id) {

        Payment payment = paymentMapper.selectByPrimaryKey(id);

        return ServerResponse.createSuccessData("get success by"+serverPort,payment);
    }
}
