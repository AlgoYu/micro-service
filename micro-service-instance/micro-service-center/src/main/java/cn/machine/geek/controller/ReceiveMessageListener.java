package cn.machine.geek.controller;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageListener {

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        System.out.println("接受到消息：" + message.getPayload());
    }
}
