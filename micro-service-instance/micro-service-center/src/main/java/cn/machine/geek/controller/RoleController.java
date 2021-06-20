package cn.machine.geek.controller;

import cn.machine.geek.common.P;
import cn.machine.geek.common.R;
import cn.machine.geek.dto.RoleAuthority;
import cn.machine.geek.entity.Role;
import cn.machine.geek.entity.RoleAuthorityRelation;
import cn.machine.geek.service.AuthorityService;
import cn.machine.geek.service.RoleAuthorityRelationService;
import cn.machine.geek.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 角色控制器
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService; //这个角色服务
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityRelationService roleAuthorityRelationService;

    /**
     * @param
     * @Author: MachineGeek
     * @Description: 获取所有角色
     * @Date: 2021/1/14
     * @Return: cn.machine.geek.common.R
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(roleService.list());
    }

    /**
     * @param id
     * @Author: MachineGeek
     * @Description: 根据ID获取
     * @Date: 2021/1/7
     * @Return: cn.machine.geek.common.R
     */
    @GetMapping("/getWithAuthorityById")
    @PreAuthorize("hasAuthority('ROLE:GET')")
    public R getWithAuthorityById(@RequestParam("id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("role", roleService.getById(id));
        map.put("authorities", authorityService.listByRoleId(id));
        return R.ok(map);
    }

    /**
     * @param p
     * @Author: MachineGeek
     * @Description: 分页获取
     * @Date: 2021/1/7
     * @Return: cn.machine.geek.common.R
     */
    @GetMapping("/paging")
    @PreAuthorize("hasAuthority('ROLE:GET')")
    public R paging(@Validated P p) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        String keyword = p.getKeyword();
        if (!StringUtils.isEmpty(keyword)) {
            queryWrapper.lambda().like(Role::getName, keyword)
                    .like(Role::getKey, keyword);
        }
        return R.ok(roleService.page(new Page<>(p.getPage(), p.getSize())));
    }

    /**
     * @param roleAuthority
     * @Author: MachineGeek
     * @Description: 添加
     * @Date: 2021/1/7
     * @Return: cn.machine.geek.common.R
     */
    @PostMapping("/addWithAuthority")
    @Transactional
    @PreAuthorize("hasAuthority('ROLE:ADD')")
    public R addWithAuthority(@RequestBody RoleAuthority roleAuthority) {
        if (!roleAuthority.getKey().startsWith("ROLE_")) {
            roleAuthority.setKey("ROLE_" + roleAuthority.getKey());
        }
        roleAuthority.setCreateTime(LocalDateTime.now());
        if (roleService.save(roleAuthority)) {
            addAuthorities(roleAuthority.getId(), roleAuthority.getAuthorityIds());
            return R.ok();
        } else {
            return R.fail("添加失败");
        }
    }

    /**
     * @param roleAuthority
     * @Author: MachineGeek
     * @Description: 修改
     * @Date: 2021/1/7
     * @Return: cn.machine.geek.common.R
     */
    @PutMapping("/modifyWithAuthorityById")
    @Transactional
    @PreAuthorize("hasAuthority('ROLE:MODIFY')")
    public R modifyWithAuthorityById(@RequestBody RoleAuthority roleAuthority) {
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Role::getId, roleAuthority.getId())
                .set(Role::getName, roleAuthority.getName())
                .set(Role::getUpdateTime, LocalDateTime.now());
        addAuthorities(roleAuthority.getId(), roleAuthority.getAuthorityIds());
        return R.ok(roleService.update(updateWrapper));
    }

    /**
     * @param id
     * @Author: MachineGeek
     * @Description: 通过ID删除
     * @Date: 2021/1/7
     * @Return: cn.machine.geek.common.R
     */
    @DeleteMapping("/deleteById")
    @PreAuthorize("hasAuthority('ROLE:DELETE')")
    public R deleteById(@RequestParam("id") Long id) {
        return R.ok(roleService.removeById(id));
    }

    /**
     * @param roleId
     * @param authorityIds
     * @Author: MachineGeek
     * @Description: 添加角色与权力的绑定关系
     * @Date: 2021/1/11
     * @Return: void
     */
    private void addAuthorities(Long roleId, List<Long> authorityIds) {
        // 清除这个角色所有的关系
        QueryWrapper<RoleAuthorityRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleAuthorityRelation::getRoleId, roleId);
        roleAuthorityRelationService.remove(queryWrapper);
        // 添加新的关系
        if (authorityIds != null && authorityIds.size() > 0) {
            List<RoleAuthorityRelation> roleAuthorityRelations = new ArrayList<>();
            authorityIds.forEach((authorityId) -> {
                RoleAuthorityRelation roleAuthorityRelation = new RoleAuthorityRelation();
                roleAuthorityRelation.setRoleId(roleId);
                roleAuthorityRelation.setAuthorityId(authorityId);
                roleAuthorityRelations.add(roleAuthorityRelation);
            });
            roleAuthorityRelationService.saveBatch(roleAuthorityRelations);
        }
    }
}