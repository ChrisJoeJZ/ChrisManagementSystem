package com.chris.service;

import com.chris.entity.Admin;
import com.chris.entity.bo.AdminAddBo;
import com.chris.entity.bo.AdminSearchBo;
import com.chris.entity.bo.AdminUpdateBo;
import com.chris.entity.bo.PageBo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 用户类的业务逻辑层接口
 * 登录验证 业务逻辑层 接口
 */
public interface AdminService extends UserDetailsService {

    /**
     * 根据筛选条件查询符合条件的员工列表
     * @param adminSearchBo
     * @param pageBo
     * @return
     */
    List<Admin> listBySearchBo(AdminSearchBo adminSearchBo, PageBo pageBo);

    boolean saveOne(AdminAddBo adminAddBo);

    Admin getAdminById(Integer id);

    boolean updateByAdminUpdateBo(AdminUpdateBo adminUpdateBo);


    boolean deleteByAdminId(Integer adminId);

    boolean deleteByIdList(Integer[] idList);



}
