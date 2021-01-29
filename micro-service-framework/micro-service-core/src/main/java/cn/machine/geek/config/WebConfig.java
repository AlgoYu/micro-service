package cn.machine.geek.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: MachineGeek
 * @Description: Web服务器配置类
 * @Date: 2020/10/4
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /** @Author: MachineGeek
     * @Description: 跨域配置
     * @Date: 2020/10/4
     * @param registry
     * @Return void
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }
}
