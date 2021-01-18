package cn.machine.geek.dto;

import cn.machine.geek.entity.Account;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 账号角色
 * @Email: 794763733@qq.com
 * @Date: 2021/1/11
 */
@Data
public class AccountRole extends Account {
    private List<Long> roleIds;
}
