package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 新建一个角色实体类，将他变成授权类
 */
@Data
public class Role implements GrantedAuthority {

    @TableId
    private Integer roleId;
    private String roleName;

    // 获取授权的名称
    public String getAuthority() {
        // 返回角色名称作为授权名称
        return getRoleName();
    }
}
