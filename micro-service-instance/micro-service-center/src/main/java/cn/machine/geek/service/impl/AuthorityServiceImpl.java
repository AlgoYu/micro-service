package cn.machine.geek.service.impl;

import cn.machine.geek.entity.Authority;
import cn.machine.geek.mapper.AuthorityMapper;
import cn.machine.geek.service.AuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 权力服务实现类
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@Service
public class AuthorityServiceImpl extends ServiceImpl<AuthorityMapper, Authority> implements AuthorityService {
    @Override
    public List<Authority> listByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Authority> listByAccountId(Long accountId) {
        return baseMapper.selectByAccountId(accountId);
    }
}
