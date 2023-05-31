package com.chris.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chris.entity.Permission;
import com.chris.entity.vo.PermissionWithRoleVo;
import com.chris.mapper.PermissionMapper;
import com.chris.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> list() {
        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>());
        return permissions;
    }

    @Override
    public List<PermissionWithRoleVo> listVoByRoleId(Integer roleId) {
        List<PermissionWithRoleVo> list = permissionMapper.listVoWithRoleId(roleId);
        return list;
    }


}
