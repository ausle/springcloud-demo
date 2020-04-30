package com.asule.springcloud.controller;

import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import com.asule.springcloud.service.IPaymentService;
import com.netflix.discovery.shared.Applications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * created by asule on 2020-04-28 11:47
 */
@Controller
@Slf4j
public class PaymentController {

    @Autowired
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;

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





    //通过discoveryClient获取服务信息
    @GetMapping(value="/discovery/getService")
    @ResponseBody
    public ServerResponse<Payment> getService() {
        List<String> services = discoveryClient.getServices();
        for (String service:
             services) {
            log.info("service,{}",service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PROVIDER-PAYMENT");
        for (ServiceInstance instance:instances) {
            log.info("serviceId:{},host:{},port:{}",instance.getInstanceId(),instance.getHost(),instance.getPort());
        }
        return ServerResponse.createSuccess();
    }


    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return serverPort;
        }
    }


}
