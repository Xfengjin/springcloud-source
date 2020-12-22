package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    String paymentInfo_Ok(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_Ok(id);
        return result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
//                    })
    @HystrixCommand
    String paymentInfo_Timeout(@PathVariable("id") Integer id) {
        int age = 10 /0;
        String result = paymentHystrixService.paymentInfo_Timeout(id);
        return result;
    }

    /*public String paymentInfo_TimeoutHandler(@PathVariable("id") Integer id) {
        return "当前线程为： " + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler, id: " + id + ",服务降级方法,消费侧";
    }*/

    // 以下是全局fallback方法
    public String payment_Global_FallbackMethod() {
        return "Global异常处理信息, 请稍后再试。";
    }

}
