package cn.machine.geek.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @Author: MachineGeek
 * @Description: 自定义客户端细节
 * @Email: 794763733@qq.com
 * @Date: 2021/1/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomClientDetail implements ClientDetails {
    private String clientId;
    private Set<String> resourceIds;
    private String clientSecret;
    private Set<String> scoped;
    private Set<String> AuthorizedGrantTypes;
    private Set<String> registeredRedirectUri;
    private Collection<GrantedAuthority> authorities;
    private Integer accessTokenValiditySeconds;
    private Integer refreshTokenValiditySeconds;
    private Boolean autoApprove;
    private Map<String, Object> additionalInformation;
    @Override
    public String getClientId() {
        return clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        return scoped;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return AuthorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return autoApprove;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }
}
