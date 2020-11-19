package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 测试控制器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/19
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @GetMapping(value = "/get")
    public R getTest(){
        return R.ok("Get测试OK");
    }
}
