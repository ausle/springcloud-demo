package com.asule.springcloud.controller;

import com.asule.springcloud.entities.Payment;
import com.asule.springcloud.entities.ServerResponse;
import com.asule.springcloud.service.HystrixPaymentServiceImpl;
import com.asule.springcloud.service.IHystrixPaymentService;
import com.asule.springcloud.service.IPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * created by asule on 2020-04-29 21:29
 */

@Controller
@DefaultProperties(defaultFallback ="paymentInfoGlobal")
public class OrderController {
    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private HystrixPaymentServiceImpl hystrixPaymentService;


    @PostMapping(value="/consumer/payment/create")
    @ResponseBody
    public ServerResponse create(Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping(value="/consumer/payment/getPayment/{id}")
    @ResponseBody
    public ServerResponse getPaymentById(@PathVariable("id")Long id) {
        return paymentService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentOpenFeignTimeout(){
        return paymentService.paymentOpenFeignTimeout();
    }


//    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    @ResponseBody
    public String paymentHystrixOK(@PathVariable("id")Integer id){
       String paymentInfoByOK = hystrixPaymentService.paymentInfoByOK(id);
        return paymentInfoByOK;
    }


    /*
        也可以在消费端进行fallback。
        这里设置的超时时间，指的是等待服务响应的时间。若超过1500毫秒，则fallback。
    */
//    @HystrixCommand(fallbackMethod = "paymentInfoDefault",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
//    @HystrixCommand
    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @ResponseBody
    public String paymentHystrixtimeout(@PathVariable("id")Integer id){
        String paymentInfoByTimeout = hystrixPaymentService.paymentInfoByTimeout(id);
        return paymentInfoByTimeout;
    }

    public String paymentInfoDefault(@PathVariable("id")Integer id){
        return "线程：" + Thread.currentThread().getName()+"消费端-fallback~~~~~"+"id:"+id;
    }


    /*
       若对消费端的每个接口都进行fallback指定，会导致代码冗余。
       解决的方式，可以使用@DefaultProperties，声明全局的fallback。
       若要使用全局的fallback，在消费端的请求上添加@HystrixCommand即可。
       若要声明自己的fallback，在@HystrixCommand上指定自己的fallback。
     */
    public String paymentInfoGlobal(){
        return "线程：" + Thread.currentThread().getName()+"消费端-global-fallback~~~~~";
    }


    /*
        fallback的处理与业务逻辑代码混在一起，看起来很混乱。怎么解决这个问题呢?
        在service中，我们通过feign对服务端的每个请求接口都完成绑定。
        可以定义一个类去实现该service。在接口方法实现中，为每个服务端的接口定义fallback。

        见：HystrixPaymentServiceImpl
    */
}
