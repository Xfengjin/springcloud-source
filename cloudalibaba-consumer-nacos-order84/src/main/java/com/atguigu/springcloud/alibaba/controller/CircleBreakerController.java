package com.atguigu.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.alibaba.service.PaymentService;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.sun.deploy.security.BlockedException;
import io.micrometer.core.instrument.Meter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Slf4j
public class CircleBreakerController {

    private static final String SERVER_URL = "http://nacos-payment-provider";

    @Resource
    private RestTemplate restTemplate;

    @RequestMapping("/consumer/fallback/{id}")
    // @SentinelResource(value = "fallback") // 没有配置
    // @SentinelResource(value = "fallback", fallback = "handlerFallback") // fallback只负责业务异常
    // @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler只负责sentinel控制台违规
    // @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler") // fallback只负责业务异常
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler", exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult fallback(@PathVariable("id") Long id) {
        CommonResult result = restTemplate.getForObject(SERVER_URL + "/paymentSql/" + id, CommonResult.class, id);

        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException, 参数异常。。。。。。。。。。");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException, 没有指定记录");
        }
        return result;
    }

    /**
     * fallback只负责业务异常
     * @param id
     * @param e
     * @return
     */
    public CommonResult handlerFallback(Long id, Throwable e) {
        Payment payment = new Payment(id, null);
        return new CommonResult(444, "兜底异常handlerFallback， 异常内容：" + e);
    }

    /**
     * blockHandler只负责sentinel控制台违规
     * @param id
     * @param exception
     * @return
     */
    public CommonResult blockHandler(Long id, BlockException exception) {
        Payment payment = new Payment(id, null);
        return new CommonResult(445, "sentinel限流，兜底异常blockHandler， 异常内容：" + exception);
    }


    // ====================openfeign
    @Resource
    private PaymentService paymentService;

    @GetMapping(value = "/consumer/openfeign/{id}")
    public CommonResult paymentSql(@PathVariable("id") Long id) {
        return paymentService.paymentSql(id);
    }
}
