package cn.machine.geek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: MachineGeek
 * @Description: 测试服务
 * @Email: 794763733@qq.com
 * @Date: 2021/1/20
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}
