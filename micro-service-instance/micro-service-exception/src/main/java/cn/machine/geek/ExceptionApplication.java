package cn.machine.geek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: MachineGeek
 * @Description: 异常应用程序
 * @Email: 794763733@qq.com
 * @Date: 2021/1/31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ExceptionApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExceptionApplication.class, args);
    }
}
