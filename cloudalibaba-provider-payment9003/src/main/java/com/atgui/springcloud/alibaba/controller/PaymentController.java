package com.atgui.springcloud.alibaba.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.netflix.ribbon.proxy.annotation.Var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    private static Map<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "11111111111111111111111111111"));
        hashMap.put(2L, new Payment(2L, "22222222222222222222222222222"));
        hashMap.put(3L, new Payment(3L, "33333333333333333333333333333"));
    }

    @GetMapping("/paymentSql/{id}")
    public CommonResult paymentSql(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new CommonResult(200, "from mysql, serverPort: " + serverPort, payment);
    }

}
