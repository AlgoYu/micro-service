package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 账户角色关系类
 * @Date: 2021/1/6
 */
@Data
public class AccountRoleRelation {
    @TableField(value = "`account_id`")
    private Long accountId;

    @TableField(value = "`role_id`")
    private Long roleId;
}
