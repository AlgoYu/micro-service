package cn.machine.geek.oauth;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 自定义Token携带信息
 * @Email: 794763733@qq.com
 * @Date: 2021/1/19
 */
@Component
public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String,Object> info = new HashMap<>();
        CustomUserDetail customUserDetail = (CustomUserDetail) authentication.getPrincipal();
        info.put("id",customUserDetail.getId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
