package com.atguigu.springcloud.alibaba.service;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
public interface StorageService {

    // 减库存
    void decrease(Long productId, Integer count);

}
