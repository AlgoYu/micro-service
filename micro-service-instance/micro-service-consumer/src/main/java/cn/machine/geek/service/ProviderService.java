package cn.machine.geek.service;

import cn.machine.geek.common.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: MachineGeek
 * @Description: 消费提供者服务
 * @Email: 794763733@qq.com
 * @Date: 2020/11/23
 */
@FeignClient(value = "MICRO-PROVIDER-SERVICE")
@Service
public interface ProviderService {
    @GetMapping(value = "/provider/get")
    R get();

    @PostMapping(value = "/provider/post")
    R post(Object object);
}
