package com.chris.mapper;

import com.chris.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.bo.AdminAddBo;
import com.chris.entity.bo.AdminSearchBo;
import com.chris.entity.bo.AdminUpdateBo;
import com.chris.entity.bo.PageBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 根据用户名查询到一个带有角色列表的用户对象
     * @param name
     * @return
     */
    Admin getAdminByName(@Param("name") String name);

    /**
     * 根据管理员编号获取到对象
     * @param id
     * @return
     */
    Admin getAdminById(@Param("id") Integer id);

    /**
     * 根据传入的adminBo对象修改admin
     * @param adminUpdateBo
     * @return
     */
    int updateByAdminUpdateBo(@Param("adminUpdateBo") AdminUpdateBo adminUpdateBo);

    int batchDeleteRoleByAdminId(@Param("adminUpdateBo") AdminUpdateBo adminUpdateBo);

    /**
     * 根据筛选条件查询员工列表
     * @param adminSearchBo
     * @return
     */
    List<Admin> listByAdminSearchBo(@Param("adminSearchBo") AdminSearchBo adminSearchBo,
                                    @Param("pageBo") PageBo pageBo);

    /**
     * 根据筛选条件查询出符合筛选条件的总记录数
     * @param adminSearchBo
     * @return
     */
    Long getCount(@Param("adminSearchBo") AdminSearchBo adminSearchBo);

    /**
     * 向Admin表插入数据
     * @param adminAddBo
     * @return
     */
    int insert(@Param("adminAddBo") AdminAddBo adminAddBo);

    /**
     * 批量插入用户-橘色关系
     *
     * @param adminAddBo
     * @return
     */
    int batchInsertAdminRole(@Param("adminAddBo") AdminAddBo adminAddBo);
}
