package cn.machine.geek.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @Author: MachineGeek
 * @Description: 资源配置
 * @Email: 794763733@qq.com
 * @Date: 2020/11/26
 */
@Configuration
public class ResourceConfig {

    /**
    * @Author: MachineGeek
    * @Description: 注册Token存储策略
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.TokenStore
    */
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
    * @Author: MachineGeek
    * @Description: 注册JWT转换器
    * @Date: 2020/11/27
     * @param
    * @Return: org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
    */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("pub");
        String publicKey = null;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        jwtAccessTokenConverter.setVerifier(new RsaVerifier(publicKey));
        return jwtAccessTokenConverter;
    }
}
