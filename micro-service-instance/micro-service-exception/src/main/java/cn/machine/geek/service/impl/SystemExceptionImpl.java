package cn.machine.geek.service.impl;

import cn.machine.geek.entity.SystemException;
import cn.machine.geek.mapper.SystemExceptionMapper;
import cn.machine.geek.service.SystemExceptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @Author: MachineGeek
 * @Description: 系统异常服务类
 * @Date: 2020/11/6
 */
@Service
public class SystemExceptionImpl extends ServiceImpl<SystemExceptionMapper, SystemException> implements SystemExceptionService {
}
