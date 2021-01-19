package cn.machine.geek.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @Author: MachineGeek
 * @Description: 资源服务器配置
 * @Email: 794763733@qq.com
 * @Date: 2021/1/19
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * @Author: MachineGeek
     * @Description: 注册JWT存储
     * @Date: 2021/1/18
     * @Return: org.springframework.security.oauth2.provider.token.TokenStore
     */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * @Author: MachineGeek
     * @Description: JWT转换器加载公钥
     * @Date: 2021/1/19
     * @param
     * @Return: org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("MachineGeek.pub");
        String pubKey = null;
        try {
            pubKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jwtAccessTokenConverter.setVerifierKey(pubKey);
        return jwtAccessTokenConverter;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore());
    }
}
