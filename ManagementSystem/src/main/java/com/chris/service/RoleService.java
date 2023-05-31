package com.chris.service;

import com.chris.entity.Role;
import com.chris.entity.bo.PageBo;

import java.util.List;

public interface RoleService {

    List<Role> list();

    /**
     * 根据角色和pageBo查询符合条件的角色列表
     * @param role
     * @param pageBo
     * @return
     */
    List<Role> listByRoleAndPageBo(Role role, PageBo pageBo);

    /**
     * 根据角色Id删除角色
     * @param id
     * @return
     */
    boolean deleteById(Integer id);

    /**
     * 根据IDLIST批量删除角色
     * @param idList
     * @return
     */
    boolean deleteByRoleIdList(Integer[] idList);

    /**
     * 添加角色
     * @param roleName
     * @param idList
     * @return
     */
    boolean insert(String roleName,Integer[] idList);


    /**
     * 校验新添加的角色名称是否存在
     * @param roleName
     * @return
     */
    boolean isRoleNameExists(String roleName);

    /**
     * 根据角色ID查询角色
     * @param roleId
     * @return
     */
    Role getById(Integer roleId);

    boolean updateOne(Integer roleId, String roleName, Integer[] permissionIdList);

}
