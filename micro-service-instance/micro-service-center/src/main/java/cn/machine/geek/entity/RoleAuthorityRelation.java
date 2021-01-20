package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 角色权力关系类
 * @Date: 2021/1/6
 */
@Data
public class RoleAuthorityRelation {
    @TableField(value = "`role_id`")
    private Long roleId;

    @TableField(value = "`authority_id`")
    private Long authorityId;
}
