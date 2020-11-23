package cn.machine.geek.service;

import cn.machine.geek.dto.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: MachineGeek
 * @Description: 消费提供者服务
 * @Email: 794763733@qq.com
 * @Date: 2020/11/23
 */
@FeignClient(value = "MICRO-PROVIDER-SERVICE")
public interface ProviderService {
    @GetMapping(value = "/get")
    R get();
}
