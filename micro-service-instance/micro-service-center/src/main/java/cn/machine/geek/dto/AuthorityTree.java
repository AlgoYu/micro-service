package cn.machine.geek.dto;

import cn.machine.geek.entity.Authority;
import lombok.Data;

import java.util.List;

/**
 * @Author: MachineGeek
 * @Description: 权力树节点
 * @Email: 794763733@qq.com
 * @Date: 2021/1/11
 */
@Data
public class AuthorityTree extends Authority {
    private List<AuthorityTree> child;
}
