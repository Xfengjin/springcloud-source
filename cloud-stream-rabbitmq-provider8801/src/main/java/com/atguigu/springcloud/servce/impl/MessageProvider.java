package com.atguigu.springcloud.servce.impl;

import com.atguigu.springcloud.servce.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author fengjin
 * @Slogan 致敬大师，致敬未来的你
 *
 * 这里的service不在是传统的三层结构里面的service
 * 这里的service需要直接跟rabbitMQ打交道，
 * 所以不在使用@Service注解
 *
 */
@EnableBinding(Source.class) // 定义消息的推送管道
@Slf4j
public class MessageProvider implements IMessageProvider {

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("*****serial:" + serial);

        return null;
    }
}
