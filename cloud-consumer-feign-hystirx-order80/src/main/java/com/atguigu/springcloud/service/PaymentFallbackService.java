package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "---------paymentFallbackServcie fall back paymentInfo_Ok, /(ㄒoㄒ)/~~";
    }

    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "---------paymentFallbackServcie fall back paymentInfo_Timeout, /(ㄒoㄒ)/~~";
    }
}
