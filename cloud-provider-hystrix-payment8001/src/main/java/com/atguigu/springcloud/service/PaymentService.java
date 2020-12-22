package com.atguigu.springcloud.service;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
public interface PaymentService {

    String paymentInfo_Ok(Integer id);

    String paymentInfo_Timeout(Integer id);

    String paymentCircuitBreaker(Integer id);
}
