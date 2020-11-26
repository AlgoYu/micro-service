package cn.machine.geek.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @Author: MachineGeek
 * @Description: 授权服务器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
    * @Author: MachineGeek
    * @Description: 客户端详情
    * @Date: 2020/11/26
     * @param clients
    * @Return: void
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")
                .secret(bCryptPasswordEncoder.encode("secret"))
                .resourceIds("RESOURCE")
                .authorizedGrantTypes("authorization_code","password","client_credentials","implicit","refresh_token")
                .scopes("all")
                .autoApprove(false)
                .redirectUris("http://www.baidu.com");
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
        defaultTokenServices.setClientDetailsService(clientDetailsService);
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenStore(tokenStore);
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
