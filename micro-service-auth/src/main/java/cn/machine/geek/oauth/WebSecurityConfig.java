package cn.machine.geek.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: MachineGeek
 * @Description: Web安全配置
 * @Email: 794763733@qq.com
 * @Date: 2021/1/18
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
    * @Author: MachineGeek
    * @Description: 注册认证管理器
    * @Date: 2021/1/18
     * @param
    * @Return: org.springframework.security.authentication.AuthenticationManager
    */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * @Author: MachineGeek
     * @Description: 注册密码加密器
     * @Date: 2021/1/6
     * @param
     * @Return: org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}