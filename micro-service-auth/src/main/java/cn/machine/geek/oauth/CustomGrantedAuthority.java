package cn.machine.geek.oauth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


/**
 * @Author: MachineGeek
 * @Description: 自定义权限类
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof CustomGrantedAuthority) {
            return authority.equals(((CustomGrantedAuthority) obj).getAuthority());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }

    @Override
    public String toString() {
        return authority;
    }
}
