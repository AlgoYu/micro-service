package cn.machine.geek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: MachineGeek
 * @Description: 资源配置
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
public class ResourceConfig {

    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("hahaah");
        return jwtAccessTokenConverter;
    }

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
        remoteTokenServices.setClientId("client");
        remoteTokenServices.setClientSecret("secret");
        return remoteTokenServices;
    }
}
