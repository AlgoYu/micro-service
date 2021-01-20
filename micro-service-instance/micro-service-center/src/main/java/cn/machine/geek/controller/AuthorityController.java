package cn.machine.geek.controller;

import cn.machine.geek.common.P;
import cn.machine.geek.common.R;
import cn.machine.geek.dto.AuthorityTree;
import cn.machine.geek.entity.Authority;
import cn.machine.geek.service.AuthorityService;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: MachineGeek
 * @Description: 权力控制器
 * @Email: 794763733@qq.com
 * @Date: 2021/1/7
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private AuthorityService authorityService;

    /**
    * @Author: MachineGeek
    * @Description: 根据ID获取
    * @Date: 2021/1/7
     * @param id
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/getById")
    public R getById(@RequestParam("id") Long id){
        return R.ok(authorityService.getById(id));
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
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        String keyword = p.getKeyword();
        if(!StringUtils.isEmpty(keyword)){
            queryWrapper.lambda().like(Authority::getName,keyword)
                    .like(Authority::getKey,keyword);
        }
        return R.ok(authorityService.page(new Page<>(p.getPage(),p.getSize())));
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取权力树
    * @Date: 2021/1/11
     * @param
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/tree")
    public R tree(){
        QueryWrapper<Authority> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(Authority::getSort);
        return R.ok(getChild(0l,authorityService.list(queryWrapper)));
    }

    /**
    * @Author: MachineGeek
    * @Description: 获取当前用户的权限
    * @Date: 2021/1/11
     * @param
    * @Return: cn.machine.geek.common.R
    */
    @GetMapping("/getMyAuthorities")
    public R getMyAuthorities(OAuth2Authentication oAuth2Authentication){
        // 获取当前用户ID
        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) oAuth2Authentication.getDetails();
        OAuth2AccessToken token = tokenStore
                .readAccessToken(oAuth2AuthenticationDetails.getTokenValue());
        // 获取当前用户的所有权限
        List<Authority> authorities = authorityService.listByAccountId(Long.valueOf((String) token.getAdditionalInformation().get("id")));
        // 把权限分为路由权限和API权限
        List<Authority> routes = new ArrayList<>();
        List<Authority> apis = new ArrayList<>();
        authorities.forEach((authority)->{
            if(authority.getUri() == null){
                apis.add(authority);
            }else{
                routes.add(authority);
            }
        });
        // 返回权限
        Map<String,Object> map = new HashMap<>();
        map.put("apis",apis);
        // 返回路由树
        map.put("routes",getChild(0L,routes));
        return R.ok(map);
    }

    /**
     * @Author: MachineGeek
     * @Description: 构建权限树
     * @Date: 2021/1/11
     * @param id
     * @param authorities
     * @Return: java.util.List<cn.machine.geek.dto.AuthorityTreeNode>
     */
    private List<AuthorityTree> getChild(Long id, List<Authority> authorities){
        List<AuthorityTree> child = new ArrayList<>();
        authorities.forEach((authority)->{
            if(authority.getPid().equals(id)){
                AuthorityTree authorityTree = new AuthorityTree();
                BeanUtils.copyProperties(authority,authorityTree);
                authorityTree.setChild(getChild(authorityTree.getId(),authorities));
                child.add(authorityTree);
            }
        });
        return child;
    }

    /**
    * @Author: MachineGeek
    * @Description: 添加
    * @Date: 2021/1/7
     * @param authority
    * @Return: cn.machine.geek.common.R
    */
    @PostMapping("/add")
    public R add(@RequestBody Authority authority){
        return R.ok(authorityService.save(authority));
    }

    /**
    * @Author: MachineGeek
    * @Description: 修改
    * @Date: 2021/1/7
     * @param authority
    * @Return: cn.machine.geek.common.R
    */
    @PutMapping("/modifyById")
    public R modifyById(@RequestBody Authority authority){
        return R.ok(authorityService.updateById(authority));
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
        return R.ok(authorityService.removeById(id));
    }
}