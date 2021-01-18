package cn.machine.geek.mapper;

import cn.machine.geek.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: MachineGeek
 * @Description: 账户映射
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
