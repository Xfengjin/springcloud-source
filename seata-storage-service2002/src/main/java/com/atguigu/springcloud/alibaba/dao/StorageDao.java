package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Mapper
public interface StorageDao {

    // 扣库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
