package cn.machine.geek.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author: MachineGeek
 * @Description: 资源服务器配置
 * @Email: 794763733@qq.com
 * @Date: 2021/1/19
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;
    private static final String RESOURCE_ID = "app";

    /**
    * @Author: MachineGeek
    * @Description: 配置资源服务器策略
    * @Date: 2021/1/19
     * @param resources
    * @Return: void
    */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(RESOURCE_ID)
                .tokenStore(tokenStore)
                .stateless(true);
    }
}
