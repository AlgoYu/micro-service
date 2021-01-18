package cn.machine.geek.controller;

import cn.machine.geek.common.P;
import cn.machine.geek.common.R;
import cn.machine.geek.dto.RoleAuthority;
import cn.machine.geek.entity.Role;
import cn.machine.geek.entity.RoleAuthorityRelation;
import cn.machine.geek.service.AuthorityService;
import cn.machine.geek.service.RoleAuthorityRelationService;
import cn.machine.geek.service.RoleService;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
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
@Api(tags = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityRelationService roleAuthorityRelationService;

    /**
    * @Author: MachineGeek
    * @Description: 获取所有角色
    * @Date: 2021/1/14
     * @param
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/list")
    private R list(){
        return R.ok(roleService.list());
    }

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
        map.put("role",roleService.getById(id));
        map.put("authorities",authorityService.listByRoleId(id));
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
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        String keyword = p.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(Role::getName,keyword)
                    .like(Role::getKey,keyword);
        }
        return R.ok(roleService.page(new Page<>(p.getPage(),p.getSize())));
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加
    * @Date: 2021/1/7
     * @param roleAuthority
    * @Return: cn.machine.geek.common.R
    */
    @PostMapping("/add")
    public R add(@RequestBody RoleAuthority roleAuthority){
        roleAuthority.setCreateTime(LocalDateTime.now());
        roleService.save(roleAuthority);
        if(roleAuthority.getAuthorityIds() != null && roleAuthority.getAuthorityIds().size() > 0){
            addAuthorities(roleAuthority.getId(),roleAuthority.getAuthorityIds());
        }
        return R.ok();
    }

    /**
    * @Author: MachineGeek
    * @Description: 修改
    * @Date: 2021/1/7
     * @param roleAuthority
    * @Return: cn.machine.geek.common.R
    */
    @PutMapping("/modifyById")
    public R modifyById(@RequestBody RoleAuthority roleAuthority){
        UpdateWrapper<Role> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Role::getId,roleAuthority.getId())
                .set(Role::getName,roleAuthority.getName())
                .set(Role::getUpdateTime,LocalDateTime.now());
        addAuthorities(roleAuthority.getId(),roleAuthority.getAuthorityIds());
        return R.ok(roleService.update(updateWrapper));
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
        return R.ok(roleService.removeById(id));
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加角色与权力的绑定关系
    * @Date: 2021/1/11
     * @param roleId
     * @param authorityIds
    * @Return: void
    */
    private void addAuthorities(Long roleId, List<Long> authorityIds){
        // 清除这个角色所有的关系
        QueryWrapper<RoleAuthorityRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(RoleAuthorityRelation::getRoleId,roleId);
        roleAuthorityRelationService.remove(queryWrapper);
        // 添加新的关系
        if(authorityIds != null && authorityIds.size() > 0){
            List<RoleAuthorityRelation> roleAuthorityRelations = new ArrayList<>();
            authorityIds.forEach((authorityId)->{
                RoleAuthorityRelation roleAuthorityRelation = new RoleAuthorityRelation();
                roleAuthorityRelation.setRoleId(roleId);
                roleAuthorityRelation.setAuthorityId(authorityId);
                roleAuthorityRelations.add(roleAuthorityRelation);
            });
            roleAuthorityRelationService.saveBatch(roleAuthorityRelations);
        }
    }
}