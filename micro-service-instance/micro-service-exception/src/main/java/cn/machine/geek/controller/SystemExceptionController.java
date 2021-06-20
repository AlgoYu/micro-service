package cn.machine.geek.controller;

import cn.machine.geek.common.P;
import cn.machine.geek.common.R;
import cn.machine.geek.entity.SystemException;
import cn.machine.geek.service.SystemExceptionService;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: MachineGeek
 * @Description: 系统异常控制器
 * @Date: 2020/11/6
 */
@RestController
@RequestMapping(value = "/systemException")
public class SystemExceptionController {
    @Autowired
    private SystemExceptionService systemExceptionService;

    @GetMapping(value = "/paging")
    @PreAuthorize("hasAuthority('EXCEPTION:GET')")
    public R paging(@Validated P p) {
        QueryWrapper<SystemException> queryWrapper = new QueryWrapper<>();
        String keyWord = p.getKeyword();
        if (!StringUtils.isEmpty(keyWord)) {
            queryWrapper.lambda().like(SystemException::getUri, keyWord)
                    .or().like(SystemException::getExceptionMessage, keyWord)
                    .or().like(SystemException::getExceptionClass, keyWord)
                    .or().like(SystemException::getParameter, keyWord);
        }
        return R.ok(systemExceptionService.page(new Page<>(p.getPage(), p.getSize()), queryWrapper));
    }

    @DeleteMapping(value = "/deleteById")
    @PreAuthorize("hasAuthority('EXCEPTION:DELETE')")
    public R deleteById(@RequestParam(value = "id") Long id) {
        return R.ok(systemExceptionService.removeById(id));
    }

    @DeleteMapping(value = "/clear")
    @PreAuthorize("hasAuthority('EXCEPTION:DELETE')")
    public R clear() {
        return R.ok(systemExceptionService.remove(new QueryWrapper<>()));
    }
}
