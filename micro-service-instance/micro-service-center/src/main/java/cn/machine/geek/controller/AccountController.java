package cn.machine.geek.controller;

import cn.machine.geek.common.P;
import cn.machine.geek.common.R;
import cn.machine.geek.dto.AccountRole;
import cn.machine.geek.entity.Account;
import cn.machine.geek.entity.AccountRoleRelation;
import cn.machine.geek.service.AccountRoleRelationService;
import cn.machine.geek.service.AccountService;
import cn.machine.geek.service.RoleService;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 账户控制器
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AccountRoleRelationService accountRoleRelationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
    * @Author: MachineGeek
    * @Description: 根据ID获取
    * @Date: 2021/1/7
     * @param id
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/getById")
    public R getById(@RequestParam("id") Long id){
        Map<String,Object> map = new HashMap<>();
        map.put("account",accountService.getById(id));
        map.put("roles",roleService.listByAccountId(id));
        return R.ok(map);
    }

    /**
    * @Author: MachineGeek
    * @Description: 分页获取
    * @Date: 2021/1/7
     * @param p
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/paging")
    public R paging(@Validated P p){
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        String keyword = p.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(Account::getName,keyword);
        }
        return R.ok(accountService.page(new Page<>(p.getPage(),p.getSize())));
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加
    * @Date: 2021/1/7
     * @param accountRole
    * @Return: cn.machine.geek.common.R
    */
    @PostMapping("/add")
    @GlobalTransactional
    public R add(@RequestBody AccountRole accountRole, HttpServletRequest httpServletRequest, OAuth2Authentication oAuth2Authentication){
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
        accountRole.setCreateTime(LocalDateTime.now());
        accountRole.setIp(oAuth2AuthenticationDetails.getRemoteAddress());
        accountRole.setPassword(passwordEncoder.encode(accountRole.getPassword()));
        accountRole.setEnable(false);
        accountService.save(accountRole);
        addRoles(accountRole.getId(),accountRole.getRoleIds());
        try {
            Thread.sleep(8 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok(true);
    }

    /**
    * @Author: MachineGeek
    * @Description: 修改
    * @Date: 2021/1/7
     * @param accountRole
    * @Return: cn.machine.geek.common.R
    */
    @PutMapping("/modifyById")
    public R modifyById(@RequestBody AccountRole accountRole){
        UpdateWrapper<Account> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Account::getId,accountRole.getId())
                .set(Account::getEmail,accountRole.getEmail())
                .set(Account::getMobile,accountRole.getMobile())
                .set(Account::getEnable,accountRole.getEnable())
                .set(Account::getUpdateTime,LocalDateTime.now());
        addRoles(accountRole.getId(),accountRole.getRoleIds());
        return R.ok(accountService.update(updateWrapper));
    }

    /**
    * @Author: MachineGeek
    * @Description: 通过ID删除
    * @Date: 2021/1/7
     * @param id
    * @Return: cn.machine.geek.common.R
    */
    @DeleteMapping("/modifyById")
    public R deleteById(@RequestParam("id") Long id){
        return R.ok(accountService.removeById(id));
    }

    /**
    * @Author: MachineGeek
    * @Description: 增加账户与角色的关系
    * @Date: 2021/1/11
     * @param accountId
     * @param roleIds
    * @Return: void
    */
    private void addRoles(Long accountId, List<Long> roleIds){
        // 清除这个账户所有的关系
        QueryWrapper<AccountRoleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(AccountRoleRelation::getAccountId,accountId);
        accountRoleRelationService.remove(queryWrapper);
        // 添加新的关系
        if(roleIds != null && roleIds.size() > 0){
            List<AccountRoleRelation> accountRoleRelations = new ArrayList<>();
            roleIds.forEach((roleId)->{
                AccountRoleRelation accountRoleRelation = new AccountRoleRelation();
                accountRoleRelation.setAccountId(accountId);
                accountRoleRelation.setRoleId(roleId);
                accountRoleRelations.add(accountRoleRelation);
            });
            accountRoleRelationService.saveBatch(accountRoleRelations);
        }
    }
}