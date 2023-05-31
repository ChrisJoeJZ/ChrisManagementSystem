package com.chris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.AdminRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 通过id列表批量删除员工角色关系
     * @param idList
     * @return
     */
    int deleteByAdminIds(@Param("idList") Integer[] idList);
}
