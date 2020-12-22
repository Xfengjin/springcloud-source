package com.atguigu.springclod.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
public class CustomerBlockHandler {

    public static CommonResult handlerException(BlockException exception) {
        return new CommonResult(444, "按照自定义测试Ok,global----------1");
    }

    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(444, "按照自定义测试Ok,global----------2");
    }


}
