package cn.machine.geek.service;

/**
 * @Author: MachineGeek
 * @Description: 消息提供者接口
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
public interface RabbitMessageProvider {
    boolean send(Object object);
}
