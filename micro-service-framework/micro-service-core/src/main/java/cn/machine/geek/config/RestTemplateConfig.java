package cn.machine.geek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: MachineGeek
 * @Description: 服务调用配置类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/19
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
