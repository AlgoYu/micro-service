package cn.machine.geek.service;

import cn.machine.geek.common.R;
import cn.machine.geek.dto.AccountRole;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("MICRO-CENTER-SERVICE")
@RequestMapping(value = "/account")
public interface CenterService {
    @PostMapping(value = "/add")
    R add(@RequestBody AccountRole accountRole);
}
