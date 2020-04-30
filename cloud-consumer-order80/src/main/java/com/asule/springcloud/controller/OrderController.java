package com.asule.springcloud.controller;


import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


/**
 * created by asule on 2020-04-28 11:47
 */
@Controller
public class OrderController {

//    public static final String PAYMENT_URL = "http://localhost:8001";

    //访问的是注册中心中微服务的应用名(名称)。该微服务下有多个服务可供我们调用。
    //对外暴露的是微服务的名称，但还没有指明具体调用哪个端口的微服务。
    //仅仅这样是不够的，对应的微服务名称

    public static final String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping(value="/consumer/payment/create")
    @ResponseBody
    public ServerResponse create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, ServerResponse.class);
    }

    @GetMapping(value="/consumer/payment/getPayment/{id}")
    @ResponseBody
    public ServerResponse getPaymentById(@PathVariable("id")Long id) {
        //返回响应body信息封装的对象。
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,ServerResponse.class);
    }

    @GetMapping(value="/consumer/payment/getEntity/{id}")
    @ResponseBody
    public ServerResponse getEntity(@PathVariable("id")Long id) {
        //返回ResponseEntity对象，包含有body、响应头、状态码等信息
        ResponseEntity<ServerResponse> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, ServerResponse.class);
        ServerResponse body = entity.getBody();
        if(entity.getStatusCode().is2xxSuccessful()){
            return body;
        }else{
            return ServerResponse.createError("获取失败");
        }
    }

}
