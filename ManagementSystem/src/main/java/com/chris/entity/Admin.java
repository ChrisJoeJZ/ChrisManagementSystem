package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用户类 实体模型类
 * 登录验证类
 */
@Data
public class Admin implements UserDetails {

    @TableId
    private Integer adminId;        // 管理员id
    private String adminName;       // 管理员名称
    private String adminPass;       // 账户密码
    private String adminNickname;   // 管理员昵称


    // 一个用户可以拥有多个角色
    private List<Role> roleList = new ArrayList<>();

    // 一个用户可以访问多个菜单
    private List<Menu> menuList = new ArrayList<>();


    // 获取到授权列表
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList();
    }

    // 获取到账户密码
    public String getPassword() {
        return getAdminPass();
    }

    // 获取到账户名称
    public String getUsername() {
        return getAdminName();
    }

    // 账户是否不过期
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账户凭证是否未过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否启用
    public boolean isEnabled() {
        return true;
    }
}
