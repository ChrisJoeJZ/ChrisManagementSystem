package com.chris.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.chris.entity.Role;
import com.chris.entity.bo.PageBo;
import com.chris.mapper.PermissionMapper;
import com.chris.mapper.RoleMapper;
import com.chris.mapper.RolePermissionMapper;
import com.chris.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> list() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    public List<Role> listByRoleAndPageBo(Role role, PageBo pageBo) {

        //先查寻出满足条件的记录条数
        int count = roleMapper.getCount(role);
        //给pageBo的总记录数赋值
        pageBo.setResultCount((long) count);
        //查询出满足条件的角色列表
        List<Role> roles = roleMapper.listByRoleAndPageBo(role, pageBo);

        return roles;
    }

    @Override
    public boolean deleteById(Integer id) {
        //先删除角色表的数据
        Map<String,Object> map = new HashMap<>();
        map.put("role_id",id);
        int i = roleMapper.deleteByMap(map);
        //再把角色-权限删除
        int i1 = rolePermissionMapper.deleteByMap(map);

        return i>=1 && i1>=1;
    }

    @Override
    public boolean deleteByRoleIdList(Integer[] idList) {
        //先删除表中的数据
        int i = roleMapper.deleteByRoleIdList(idList);
        //再删除角色权限关系
        int i1 = rolePermissionMapper.deleteByRoleIdList(idList);

        return i>=1 && i1>=1;
    }

    @Override
    public boolean insert(String roleName, Integer[] permissionIdList) {
            List<Role> list = roleMapper.selectList(new QueryWrapper<Role>());
            Role role = new Role();
            role.setRoleId(list.size() + 1);
            role.setRoleName(roleName);

            //插入角色
            int insert = roleMapper.insert(role);
            //插入权限
            int i = roleMapper.batchInsertRolePermission(role, permissionIdList);

            return insert >= 1 && i >= 1;
    }


    @Override
    public boolean isRoleNameExists(String roleName) {
        //使用条件构造器去比较数据库是否已经存在输入的角色名
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_name",roleName);
        Role role = roleMapper.selectOne(wrapper);

        //如果角色不是null则放回false表示不存在
        return role== null? false:true;
    }

    @Override
    public Role getById(Integer roleId) {
//        Role role = roleMapper.selectById(roleId);
//        return role;

        //创建条件构造器
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id",roleId);
        Role role = roleMapper.selectOne(wrapper);
        return role;
    }

    @Override
    public boolean updateOne(Integer roleId, String roleName, Integer[] idList) {

        Role role = new Role();
        role.setRoleId(roleId);
        role.setRoleName(roleName);

        UpdateWrapper<Role> wrapper = new UpdateWrapper<>();
        wrapper.set("role_name",roleName)
                .eq("role_id",roleId);

        //先更新角色表中的数据
        int i = roleMapper.update(role, wrapper);
        //删除原有的角色关系权限
        HashMap<String, Object> map = new HashMap<>();
        map.put("role_id",roleId);
        int i1 = rolePermissionMapper.deleteByMap(map);
        //重新添加角色权限关系
        int i2 = roleMapper.batchInsertRolePermission(role, idList);

        return i >= 1 && i1 >= 1 && i2 >= 1;

    }
}
