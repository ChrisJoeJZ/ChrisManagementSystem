package com.chris.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 权限验证类
 * 判断当前用户的角色列表中 是否含有当前请求的url所需要的角色
 */
@Component
public class PermissionValid implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {


        // 遍历当前请求所需要的角色列表(在授权类中传递过来的角色列表)
        for (ConfigAttribute configAttribute : configAttributes) {

            // 先判断当前用户是否为空
            if(authentication == null){
                // 当前用户为空说明未登录，抛出异常
                throw new AccessDeniedException("权限认证失败，用户未登录");
            }

            // 获取到当前授权的角色名称
            String needRole = configAttribute.getAttribute();

            // 判断这个所需要的角色是否是公开的权限
            if("PublicPermission".equals(needRole)){
                if(authentication instanceof AnonymousAuthenticationToken){
                    throw new BadCredentialsException("未登录");
                }else{
                    return;
                }
            }

            // 如果不是公开的权限，获取到当前登录用户所拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 遍历当前登录账户所拥有的每一个角色
            for (GrantedAuthority authority : authorities) {
                // 判断当前用户的角色是否和这个授权角色相同
                if(authority.getAuthority().equals(needRole)){
                    return;
                }
            }
        }

        // 认证失败
        throw new AccessDeniedException("权限认证失败，当前用户无权访问呢");

    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
