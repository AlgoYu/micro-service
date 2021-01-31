package cn.machine.geek.service.impl;

import cn.machine.geek.service.RabbitMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author: MachineGeek
 * @Description: 消息提供者
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
@ConditionalOnMissingBean(name = "rabbitMessageProvider")
@ConditionalOnClass(Source.class)
@EnableBinding(Source.class)
public class RabbitMessageProviderImpl implements RabbitMessageProvider {
    @Autowired
    private MessageChannel output;
    @Override
    public boolean send(Object object) {
        return output.send(MessageBuilder.withPayload(object).build());
    }
}