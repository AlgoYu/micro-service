package cn.machine.geek.mapper;

import cn.machine.geek.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 角色映射
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> selectByAccountId(@Param("accountId") Long accountId);
}
