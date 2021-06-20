package cn.machine.geek.oauth;

import cn.machine.geek.entity.Account;
import cn.machine.geek.entity.Authority;
import cn.machine.geek.entity.Role;
import cn.machine.geek.mapper.AccountMapper;
import cn.machine.geek.mapper.AuthorityMapper;
import cn.machine.geek.mapper.RoleMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 自定义用户信息
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AuthorityMapper authorityMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Account::getName, s);
        Account account = accountMapper.selectOne(queryWrapper);
        if (account == null) {
            throw new UsernameNotFoundException("账户未找到");
        }
        List<Role> roles = roleMapper.selectByAccountId(account.getId());
        List<Authority> authorities = authorityMapper.selectByAccountId(account.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        roles.forEach((role) -> {
            grantedAuthorities.add(new CustomGrantedAuthority(role.getKey()));
        });
        authorities.forEach((authority) -> {
            grantedAuthorities.add(new CustomGrantedAuthority(authority.getKey()));
        });
        return new CustomUserDetail(account.getId(), account.getName(), account.getPassword(), account.getEnable(), grantedAuthorities);
    }
}
