package cn.machine.geek.controller;

import cn.machine.geek.common.R;
import cn.machine.geek.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: MachineGeek
 * @Description: 消费者控制器
 * @Email: 794763733@qq.com
 * @Date: 2020/11/23
 */
@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {
    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "/get")
    public R get(){
        return providerService.get();
    }

    @PostMapping(value = "/post")
    public R post(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("test","It's ok!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return providerService.post(jsonObject);
    }

    @GetMapping(value = "/withoutAuthority")
    public R withoutAuthority(){
        return R.ok();
    }
}
