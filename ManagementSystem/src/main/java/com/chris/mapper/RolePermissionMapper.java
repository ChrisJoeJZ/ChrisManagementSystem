package com.chris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

    /**
     * 根据角色ID数组批量删除角色权限关系
     * @param idList
     * @return
     */
    int deleteByRoleIdList(@Param("idList") Integer[] idList);
}
