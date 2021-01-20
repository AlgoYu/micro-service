package cn.machine.geek.service.impl;

import cn.machine.geek.entity.Role;
import cn.machine.geek.mapper.RoleMapper;
import cn.machine.geek.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 角色服务实现类
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<Role> listByAccountId(Long accountId) {
        return baseMapper.selectByAccountId(accountId);
    }
}
