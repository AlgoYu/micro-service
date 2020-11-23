package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;
    private final String URL = "http://MICRO-PROVIDER-SERVICE";

    @GetMapping(value = "/get")
    public R get(){
        return restTemplate.getForObject(URL+"/provider/get",R.class);
    }
}
