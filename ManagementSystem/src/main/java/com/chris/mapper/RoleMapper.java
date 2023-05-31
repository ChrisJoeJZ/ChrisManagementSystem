package com.chris.mapper;

import com.chris.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.bo.PageBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 访问数据库中 Role 数据表的 数据访问层接口
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 根据用户id查询到这个用户所拥有的角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoleListByAdminId(@Param("adminId") Integer adminId);


    /**
     * 根据url查询到角色列表
     * @return
     */
    List<Role> listByuUrl(@Param("url") String url);

    /**
     * 查询符合条件的角色列表
     * @param role
     * @param pageBo
     * @return
     */
    List<Role> listByRoleAndPageBo(@Param("role") Role role, @Param("pageBo") PageBo pageBo);

    /**
     * 查询符合条件的记录条数
     * @param role
     * @return
     */
    int getCount(@Param("role") Role role);


    int batchInsertRolePermission(@Param("role") Role role,@Param("idList") Integer[] idList);


    /**
     * 根据idList批量删除角色
     * @param idList
     * @return
     */
    int deleteByRoleIdList(@Param("idList") Integer[] idList);



}
