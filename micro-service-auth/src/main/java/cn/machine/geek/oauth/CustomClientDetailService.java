package cn.machine.geek.oauth;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @Author: MachineGeek
 * @Description: 自定义客户端信息
 * @Email: 794763733@qq.com
 * @Date: 2021/1/18
 */
public class CustomClientDetailService implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return null;
    }
}
