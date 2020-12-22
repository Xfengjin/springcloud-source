package com.atguigu.spruingcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@EnableDiscoveryClient
@SpringBootApplication
public class NacosPaymentMain9002 {

    public static void main(String[] args) {
        SpringApplication.run(NacosPaymentMain9002.class, args);
    }

}
