package cn.machine.geek.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: MachineGeek
 * @Description: 角色
 * @Email: 794763733@qq.com
 * @Date: 2021/1/6
 */
@Data
public class Role {
    @TableId(value = "`id`",type= IdType.ASSIGN_ID)
    private Long id;

    @TableField(value = "`name`")
    private String name;

    @TableField(value = "`key`")
    private String key;

    @TableField(value = "`version`")
    private Long version;

    @TableField(value = "`create_time`")
    private LocalDateTime createTime;

    @TableField(value = "`update_time`")
    private LocalDateTime updateTime;
}
