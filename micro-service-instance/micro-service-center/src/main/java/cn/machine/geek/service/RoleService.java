package cn.machine.geek.service;

import cn.machine.geek.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 角色服务
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
public interface RoleService  extends IService<Role> {
    List<Role> listByAccountId(Long accountId);
}
