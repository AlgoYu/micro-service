package cn.machine.geek.service;

import cn.machine.geek.entity.Authority;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 权力服务
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
public interface AuthorityService  extends IService<Authority> {
    List<Authority> listByRoleId(Long roleId);
    List<Authority> listByAccountId(Long accountId);
}
