package com.chris.security;

import com.chris.entity.Role;
import com.chris.mapper.RoleMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 授权类
 * 当用户访问某个资源路径时， 如何给资源路径授予权限
 */
@Component
public class PermissionAuthority implements FilterInvocationSecurityMetadataSource {

    @Resource
    private RoleMapper roleMapper;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        // 获取到请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();

        // 根据访问的url获取到可以访问这个url的角色列表
        List<Role> list = roleMapper.listByuUrl(requestUrl);

        // 判断这个角色列表是否不为空
        if(!list.isEmpty() && list.size() > 0){
            // 不为空，并且有角色 说明当前的url受限制的，说明需要某个角色才能够访问

            // 准备一个数组存放授权列表
            String[] roles = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                roles[i] = list.get(i).getRoleName();
            }

            // 返回授权列表
            return SecurityConfig.createList(roles);
        }

        // 走到这里说明这个url不需要任何角色也能访问
        return SecurityConfig.createList("PublicPermission");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
