package com.atguigu.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
@MapperScan("com.atguigu.springcloud.alibaba.dao")
public class MybatisConfig {
}
