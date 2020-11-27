package cn.machine.geek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
public class SecurityConfig {
    /**
    * @Author: MachineGeek
    * @Description: 注册令牌存储策略
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.TokenStore
    */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(this.jwtAccessTokenConverter());
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册Jwt转换器
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
    */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("oauth2.jks"),"QQ794763733".toCharArray()).getKeyPair("MachineGeek");
        jwtAccessTokenConverter.setKeyPair(keyPair);
        return jwtAccessTokenConverter;
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册JDBC客户端详情服务
    * @Date: 2020/11/27
     * @param dataSource
    * @Return: org.springframework.security.oauth2.provider.ClientDetailsService
    */
    @Bean
    public ClientDetailsService clientDetailsService(DataSource dataSource){
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        clientDetailsService.setPasswordEncoder(this.bCryptPasswordEncoder());
        return clientDetailsService;
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册密码哈希编码器
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
    */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册验证码服务
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.oauth2.provider.code.AuthorizationCodeServices
    */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices(DataSource dataSource){
        return new JdbcAuthorizationCodeServices(dataSource);
    }
}
