package cn.machine.geek.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * @Author: MachineGeek
 * @Description: 认证服务器配置
 * @Email: 794763733@qq.com
 * @Date: 2021/1/18
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    // 用户数据服务
    @Autowired
    private UserDetailsService userDetailsService;
    // Redis连接
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    // 数据源
    @Autowired
    private DataSource dataSource;

    /**
    * @Author: MachineGeek
    * @Description: 注册RedisToken存储器
    * @Date: 2021/1/18
    * @Return: org.springframework.security.oauth2.provider.token.TokenStore
    */
    @Bean
    public TokenStore tokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("oauth");
        return  redisTokenStore;
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册Token服务
    * @Date: 2021/1/18
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.DefaultTokenServices
    */
    /*@Bean
    public DefaultTokenServices defaultTokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setReuseRefreshToken(true);
        defaultTokenServices.setAccessTokenValiditySeconds(12 * 60 & 60);
        defaultTokenServices.setRefreshTokenValiditySeconds(7 * 24 * 60 * 60);
        return defaultTokenServices;
    }*/

    /**
     * @Author: MachineGeek
     * @Description: 注册JDBC客户端服务
     * @Date: 2021/1/18
     * @param
     * @Return: org.springframework.security.oauth2.provider.client.JdbcClientDetailsService
     */
    @Bean
    public JdbcClientDetailsService jdbcClientDetailsService(){
        return new JdbcClientDetailsService(dataSource);
    }

    /**
    * @Author: MachineGeek
    * @Description: 配置用户服务、redis的Token存储器、Token服务、认证管理器
    * @Date: 2021/1/18
     * @param endpoints
    * @Return: void
    */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
//                .tokenServices(defaultTokenServices())
                .tokenStore(tokenStore());
    }

    /**
    * @Author: MachineGeek
    * @Description: 配置客户端信息
    * @Date: 2021/1/18
     * @param clients
    * @Return: void
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(jdbcClientDetailsService());
    }

    /**
    * @Author: MachineGeek
    * @Description: 配置令牌端点的安全约束
    * @Date: 2021/1/18
     * @param security
    * @Return: void
    */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
