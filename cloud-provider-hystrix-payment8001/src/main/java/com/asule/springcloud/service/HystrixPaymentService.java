package com.asule.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * created by asule on 2020-04-30 10:15
 */
@Service
public class HystrixPaymentService {

    public String paymentInfoByOK(Integer id) {
        return "线程池: "+ Thread.currentThread().getName()+"id:" + id + "正常访问！";
    }

    /*
        设置超时的上限，一旦超过该时间，就会进行服务降级。调用指定的服务降级方法。
        当方法发生异常时，无论是否超过超时时间，会自动调用fallback指定的method。
    */
    @HystrixCommand(fallbackMethod = "paymentInfoDefault",commandProperties = {
       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
    })
    public String paymentInfoByTimeout(Integer id) {
        int timeNumber =1000;
        try {
            TimeUnit.SECONDS.sleep(timeNumber);
        } catch (Exception e){
            e.printStackTrace();
        }
            return "线程池：" + Thread.currentThread().getName()
                    + " id:" + id + " 耗时(秒):" + timeNumber;
    }

    public String paymentInfoDefault(Integer id) {
        return "线程：" + Thread.currentThread().getName()+"服务端-fallback~~~~~"+"id:"+id;
    }




    /*
        关于hystrix的服务熔断：

        影响断路器打开的因素有：
            快找时间窗：指的是统计的时间范围。该时间内，断路器会统计请求和错误信息。
            请求阀值：指的是请求的数量，如果在统计的时间内，请求没有超过该阀值，即使请求全部失败，也不会打开断电器。
            失败的请求百分比：即在统计时间内，请求失败的占请求总数的百分比。前提，请求总数要达到阀值。

        满足以上条件后，断电器就会打开，发生熔断。
        发生熔断后，在一段休眠时间内(默认是5秒)，所有的请求都会交由服务降级处理。
        当休眠时间过后，此时断电器处于半开的状态，会接受请求，若该请求正常返回，那么断电器关闭。若依旧请求失败，继续下一段休眠时间，由服务降级处理。

        通过断电器，实现了把降级处理逻辑切换为主逻辑，减少了相应失败的次数。并且也可以慢慢的从半开状态恢复过来。
    */

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),                  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),     //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗，默认是5000毫秒
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),  //失败的请求百分比
    })

//    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000")，指的是统计时间，默认值是10000毫秒=10秒

    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if(id < 0){
            throw  new RuntimeException("****id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return  Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreakerFallback(@PathVariable("id") Integer id){
        return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }


}
