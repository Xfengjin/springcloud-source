package com.atguigu.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * 正常访问
     * @param id
     * @return
     */
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "当前线程为： " + Thread.currentThread().getName() + " paymentInfo_Ok, id: " + id;
    }

    /***************************************服务降级****************************************/
    /**
     * 访问超时 服务降级
     * @param id
     * @return
     */
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfo_Timeout(Integer id) {

        int timeNum = 5;

        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // int age = 10 / 0 ;
        return "当前线程为： " + Thread.currentThread().getName() + " paymentInfo_Timeout, id: " + id + "\t" + "耗时" + timeNum + "秒钟。";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "当前线程为： " + Thread.currentThread().getName() + " paymentInfo_TimeoutHandler, id: " + id + ",服务降级方法";
    }

    /***************************************服务熔断****************************************/
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("********id，不能为复数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+ "\t" + "调用成功，流水号" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能为负数， 请稍后再试，/(ㄒoㄒ)/~~ id ：" + id;
    }
}
