package cn.machine.geek.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
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
@ConditionalOnClass(ResourceServerConfigurerAdapter.class)
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    // 资源服务器名称
    private static final String RESOURCE_ID = "app";
    // RSA公钥文件名
    private static final String RSA_PUBLIC_KEY_FILE_NAME = "MachineGeek.pub";
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * @Author: MachineGeek
     * @Description: 注册Token存储策略为JWT
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
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 加载公钥
        ClassPathResource pubKey = new ClassPathResource(RSA_PUBLIC_KEY_FILE_NAME);
        // 设置公钥
        try {
            String pubStr = new String(FileCopyUtils.copyToByteArray(pubKey.getInputStream()));
            converter.setVerifier(new RsaVerifier(pubStr));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return converter;
    }

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
                .tokenStore(tokenStore())
                .stateless(true);
    }

    /**
     * @Author: MachineGeek
     * @Description: 配置资源服务器安全策略
     * @Date: 2021/1/19
     * @param http
     * @Return: void
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler(objectMapper))
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint(objectMapper))
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
    }
}
