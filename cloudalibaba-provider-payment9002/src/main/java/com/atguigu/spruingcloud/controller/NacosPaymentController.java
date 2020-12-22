package com.atguigu.spruingcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
public class NacosPaymentController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{id}")
    public String  getPayment(@PathVariable("id") Integer id) {
        return "nacos registry , serverPort: " + serverPort + "\t id" + id;
    }

}
