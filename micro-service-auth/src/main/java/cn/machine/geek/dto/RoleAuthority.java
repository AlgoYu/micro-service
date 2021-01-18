package cn.machine.geek.dto;

import cn.machine.geek.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 角色权力
 * @Email: 794763733@qq.com
 * @Date: 2021/1/11
 */
@Data
public class RoleAuthority extends Role {
    private List<Long> authorityIds;
}
