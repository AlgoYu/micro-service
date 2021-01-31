package cn.machine.geek.message;

import cn.machine.geek.entity.SystemException;
import cn.machine.geek.mapper.SystemExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private SystemExceptionMapper systemExceptionMapper;

    /**
    * @Author: MachineGeek
    * @Description: 接受消息队列的错误信息并插入到数据库
    * @Date: 2021/1/31
     * @param message
    * @Return: void
    */
    @StreamListener(Sink.INPUT)
    public void input(Message<SystemException> message){
        systemExceptionMapper.insert(message.getPayload());
    }
}
