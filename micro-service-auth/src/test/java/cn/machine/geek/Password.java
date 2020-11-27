package cn.machine.geek;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: MachineGeek
 * @Description:
 * @Email: 794763733@qq.com
 * @Date: 2020/11/27
 */
@SpringBootTest
@Slf4j
public class Password {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void password(){
        log.info(passwordEncoder.encode("RESOURCE"));
    }
}
