package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Payment;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(Long id);
}
