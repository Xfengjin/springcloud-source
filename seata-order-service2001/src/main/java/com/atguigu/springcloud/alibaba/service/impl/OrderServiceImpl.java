package com.atguigu.springcloud.alibaba.service.impl;

import com.atguigu.springcloud.alibaba.dao.OrderDao;
import com.atguigu.springcloud.alibaba.domain.Order;
import com.atguigu.springcloud.alibaba.service.AccountService;
import com.atguigu.springcloud.alibaba.service.OrderService;
import com.atguigu.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private StorageService storageService;

    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("---------->开始新建订单");
        orderDao.create(order);

        log.info("---------->订单微服务开始调用库存，减库存");
        storageService.decrease(order.getProductId(), order.getCount());

        log.info("---------->订单微服务开始调用账户，扣钱");
        accountService.decrease(order.getUserId(), order.getMoney());

        // 修改订单的状态 0 → 1
        log.info("---------->修改订单状态");
        orderDao.update(order.getUserId(), 0);

        log.info("---------->下单成功");
    }
}
