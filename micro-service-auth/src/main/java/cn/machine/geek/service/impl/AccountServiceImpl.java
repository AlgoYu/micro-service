package cn.machine.geek.service.impl;

import cn.machine.geek.entity.Account;
import cn.machine.geek.mapper.AccountMapper;
import cn.machine.geek.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 账户服务实现类
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {
}
