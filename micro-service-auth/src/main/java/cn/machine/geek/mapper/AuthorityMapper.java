package cn.machine.geek.mapper;

import cn.machine.geek.entity.Authority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 权力映射
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Mapper
public interface AuthorityMapper extends BaseMapper<Authority> {
    List<Authority> selectByAccountId(@Param("accountId")Long accountId);
    List<Authority> selectByRoleId(@Param("roleId")Long roleId);
}
