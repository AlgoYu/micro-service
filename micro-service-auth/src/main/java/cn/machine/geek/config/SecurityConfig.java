package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @Author: MachineGeek
 * @Description: 安全配置类
 * @Email: 794763733@qq.com
 * @Date: 2020/11/25
 */
@Configuration
public class SecurityConfig {
    @Autowired
    private ClientDetailsService clientDetailsService;

    /**
    * @Author: MachineGeek
    * @Description: 注册Token存储策略
    * @Date: 2020/11/25
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.TokenStore
    */
    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    /**
     * @Author: MachineGeek
     * @Description: 注册Token服务
     * @Date: 2020/11/25
     * @param
     * @Return: org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
     */
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(this.tokenStore());
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);
        return defaultTokenServices;
    }

    /** @Author: MachineGeek
     * @Description: 注册密码加密器
     * @Date: 2020/10/4
     * @param
     * @Return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册授权码服务
    * @Date: 2020/11/25
     * @param
    * @Return: org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
    */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(){
        return new InMemoryAuthorizationCodeServices();
    }
}
