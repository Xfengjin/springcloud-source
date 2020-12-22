package com.atguigu.springcloud.alibaba.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Mapper
public interface AccountDao {

    // 扣钱
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
