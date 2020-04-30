package com.asule.springcloud.controller;

import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import com.asule.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * created by asule on 2020-04-28 11:47
 */
@Controller
@Slf4j
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;






    /*
        @RequestParam接收的是ke=value的数据，而@RequestBody接收的是请求体的内容。
        它们可以同时使用，比如在POST提交中。请求体和请求路径上的参数同时出现。

        RequestBody接收请求体内容，从HTTP输入流的数据装配到目标类的对象时。有几点注意：
           (1)json中的key相同与属性名相同，且类型可以进行转换，就会调用类的setter进行属性注入。
           (2)
    */
    @PostMapping(value="/payment/create")
    @ResponseBody
    public ServerResponse create(@RequestBody Payment payment) {
        ServerResponse serverResponse = paymentService.createPayment(payment);
        return serverResponse;
    }

    //@PathVariable--->URL中占位符参数{xxx}绑定到处理器类的方法形参中
    @GetMapping(value="/payment/get/{id}")
    @ResponseBody
    public ServerResponse<Payment> getPaymentById(@PathVariable("id") Long id) {
       return paymentService.getPaymentById(id);
    }
}
