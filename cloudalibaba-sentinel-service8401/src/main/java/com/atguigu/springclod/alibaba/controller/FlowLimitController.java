package com.atguigu.springclod.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        /*try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        log.info(Thread.currentThread().getName() + "\t" + "----------testA");
        return "------------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "----------testB");
        return "------------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        /*try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testD 测试RT");*/
        log.info("testD 测试异常比例");
        int age = 10 / 0;
        return "------------testD";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey", blockHandler = "deal_testHotKey")
    public String testHotkey(@RequestParam(value = "p1", required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {

        return "-----------hotKey";
    }

    public String deal_testHotKey(String p1, String p2, BlockException exception) {
        return "-----------dealH_hotKey"; // sentinel的默认提示都是 Blocked by Sentinel (flow limiting)
    }

}
