package cn.machine.geek.controller;

import cn.machine.geek.common.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 测试控制器
 * @Email: 794763733@qq.com
 * @Date: 2021/1/20
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @PreAuthorize("hasAuthority('AAA')")
    @GetMapping(value = "/test1")
    public R test1(){
        return R.ok();
    }
    @PreAuthorize("hasAuthority('SYSTEM')")
    @GetMapping(value = "/test2")
    public R test2(){
        return R.ok();
    }
    @GetMapping(value = "/test3")
    public R test3(){
        return R.ok();
    }
}
