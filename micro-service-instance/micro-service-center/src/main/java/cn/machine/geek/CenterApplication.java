package cn.machine.geek;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: MachineGeek
 * @Description: 中心服务
 * @Email: 794763733@qq.com
 * @Date: 2021/1/20
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
@EnableAutoDataSourceProxy
public class CenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(CenterApplication.class,args);
    }
}
