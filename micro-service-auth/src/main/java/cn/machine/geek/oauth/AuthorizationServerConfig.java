package cn.machine.geek.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @Author: MachineGeek
 * @Description: 认证服务器配置
 * @Email: 794763733@qq.com
 * @Date: 2021/1/18
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    // 密码加密器
    @Autowired
    private PasswordEncoder passwordEncoder;
    // 认证管理器
    @Autowired
    private AuthenticationManager authenticationManager;
    // 用户数据服务
    @Autowired
    private UserDetailsService userDetailsService;

    /**
    * @Author: MachineGeek
    * @Description: 配置用户服务
    * @Date: 2021/1/18
     * @param endpoints
    * @Return: void
    */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    /**
    * @Author: MachineGeek
    * @Description: 自定义
    * @Date: 2021/1/18
     * @param clients
    * @Return: void
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.withClientDetails()
    }
}
