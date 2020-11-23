package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: MachineGeek
 * @Description: 测试控制器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/19
 */
@RestController
@RequestMapping(value = "/provider")
public class ProviderController {
    @GetMapping(value = "/get")
    public R get(){
        return R.ok("Get测试OK");
    }

    @PostMapping(value = "/post")
    public R post(@RequestBody Object object){
        return R.ok(object);
    }
}
