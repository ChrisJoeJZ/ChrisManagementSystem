package com.chris.service;

import com.chris.entity.Permission;
import com.chris.entity.vo.PermissionWithRoleVo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PermissionService {

    /**
     * 查询出所有权限
     * @return
     */
    List<Permission> list();

    List<PermissionWithRoleVo> listVoByRoleId(Integer roleId);

}
