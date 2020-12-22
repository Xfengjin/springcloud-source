package com.atguigu.springcloud.alibaba.dao;

import com.atguigu.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Mapper
public interface OrderDao {

    // 新建订单
    void create(Order order);

    // 修改状态 0 -> 1
    void update(@Param("userId") Long userId,  @Param("status") Integer status);

}
