package cn.machine.geek.common;

import lombok.Data;

/**
 * @Author: MachineGeek
 * @Description: 分页请求
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@Data
public class P {
    private int page;
    private int size;
    private String keyword;
}
