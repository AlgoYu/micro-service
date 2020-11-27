package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

/**
 * @Author: MachineGeek
 * @Description: 授权服务器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    // 客户端详情
    @Autowired
    private ClientDetailsService clientDetailsService;
    // Token存储策略
    @Autowired
    private TokenStore tokenStore;
    // 验证码服务
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    // JWT转换器
    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;
    /**
    * @Author: MachineGeek
    * @Description: 客户端详情
    * @Date: 2020/11/26
     * @param clients
    * @Return: void
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册令牌服务
    * @Date: 2020/11/26
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices
    */
    @Bean
    public AuthorizationServerTokenServices authorizationServerTokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        // 设置客户端详情
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        // 支持刷新Token
        defaultTokenServices.setSupportRefreshToken(true);
        // 设置Token存储策略
        defaultTokenServices.setTokenStore(tokenStore);
        // 令牌增强器
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter));
        defaultTokenServices.setTokenEnhancer(tokenEnhancerChain);
        // 设置过期时间
        defaultTokenServices.setAccessTokenValiditySeconds(7200);
        defaultTokenServices.setRefreshTokenValiditySeconds(259200);
        return defaultTokenServices;
    }

    /**
    * @Author: MachineGeek
    * @Description: 授权访问端点
    * @Date: 2020/11/26
     * @param endpoints
    * @Return: void
    */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenServices(authorizationServerTokenServices())
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    /**
    * @Author: MachineGeek
    * @Description: 端点安全策略
    * @Date: 2020/11/26
     * @param security
    * @Return: void
    */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
