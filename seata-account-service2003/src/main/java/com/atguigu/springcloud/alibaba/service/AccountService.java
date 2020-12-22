package com.atguigu.springcloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
public interface AccountService {

    // 减库存
    void decrease(Long userId, BigDecimal money);

}
