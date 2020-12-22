package com.atguigu.springcloud.alibaba.service;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult paymentSql(Long id) {
        return new CommonResult(444, "服务降级返回， --------PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
