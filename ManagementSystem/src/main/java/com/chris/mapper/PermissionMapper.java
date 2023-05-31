package com.chris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.Permission;
import com.chris.entity.vo.PermissionWithRoleVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据角色id查询权限列表
     * @param roleId
     * @return
     */
    List<Permission> listByRoleId(@Param("roleId") Integer roleId);

    /**
     * 根据角色编号 查询出角色是否拥有某个权限
     * @param roleId
     * @return
     */
    List<PermissionWithRoleVo> listVoWithRoleId(@Param("roleId") Integer roleId);
}
