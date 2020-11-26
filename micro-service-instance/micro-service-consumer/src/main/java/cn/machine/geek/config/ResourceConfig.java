package cn.machine.geek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @Author: MachineGeek
 * @Description: 资源配置
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
public class ResourceConfig {

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
        remoteTokenServices.setClientId("client");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }
}
