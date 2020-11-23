package cn.machine.geek.controller;

import cn.machine.geek.dto.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = "/post")
    public String post(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("test","It's ok!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(jsonObject.toString(),headers);
        return restTemplate.exchange(URL+"/provider/post", HttpMethod.POST,httpEntity,String.class).getBody();
    }
}
