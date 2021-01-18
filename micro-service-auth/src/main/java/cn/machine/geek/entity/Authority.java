package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 权力
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Data
public class Authority {
    @TableId(value = "`id`",type= IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "`key`")
    private String key;

    @TableField(value = "`pid`")
    private Long pid;

    @TableField(value = "`sort`")
    private Integer sort;

    @TableField(value = "`uri`")
    private String uri;
}
