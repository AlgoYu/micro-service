package cn.machine.geek.service.impl;

import cn.machine.geek.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * @Author: MachineGeek
 * @Description: 发送消息实现类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/24
 */
@EnableBinding(Source.class)
public class SendServiceImpl implements SendService {
    @Autowired
    private  MessageChannel output;

    @Override
    public void sendMessage(String message) {
        output.send(MessageBuilder.withPayload(message).build());
    }
}
