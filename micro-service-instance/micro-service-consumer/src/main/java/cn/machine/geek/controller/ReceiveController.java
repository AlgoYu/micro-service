package cn.machine.geek.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/24
 */
@Component
@EnableBinding(Sink.class)
@Slf4j
public class ReceiveController {

    @StreamListener(Sink.INPUT)
    public void input(Message<String> message){
        log.info(message.getPayload());
    }
}
