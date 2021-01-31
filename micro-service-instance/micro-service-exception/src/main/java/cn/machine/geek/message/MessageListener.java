package cn.machine.geek.message;

import cn.machine.geek.entity.SystemException;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * @Author: MachineGeek
 * @Description: 消息队列监听
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
@EnableBinding(Sink.class)
public class MessageListener {
    @StreamListener(Sink.INPUT)
    public void input(Message<SystemException> message){
        System.out.println("收到消息:"+message.getPayload().getParameter());
        System.out.println("收到消息:"+message.getPayload().getUri());
        System.out.println("收到消息:"+message.getPayload().getMethod());
        System.out.println("收到消息:"+message.getPayload().getExceptionClass());
    }
}
