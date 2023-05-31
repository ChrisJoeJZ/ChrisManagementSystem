package com.chris.service.impl;

import com.chris.entity.Admin;
import com.chris.entity.AdminRole;
import com.chris.entity.Role;
import com.chris.entity.bo.AdminAddBo;
import com.chris.entity.bo.AdminSearchBo;
import com.chris.entity.bo.AdminUpdateBo;
import com.chris.entity.bo.PageBo;
import com.chris.mapper.AdminMapper;
import com.chris.mapper.AdminRoleMapper;
import com.chris.mapper.RoleMapper;
import com.chris.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录验证 业务逻辑实现类
 */
@Service
public class AdminServiceImpl implements AdminService {


    @Resource
    private AdminMapper adminMapper;

    @Resource
    private AdminRoleMapper adminRoleMapper;



    // 根据用户名获取到 用户实体模型对象(带有角色列表)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Admin admin = adminMapper.getAdminByName(username);

        if(admin == null){
            // 如果用户不存在，则抛出异常
            throw new UsernameNotFoundException("账户名获取密码错误，请重新登录");
        }

        return admin;
    }

    /**
     * 根据筛选条件查询员工列表
     * @param adminSearchBo
     * @param pageBo
     * @return
     */
    @Override
    public List<Admin> listBySearchBo(AdminSearchBo adminSearchBo, PageBo pageBo) {
        // 查询到符合筛选条件的总记录数
        Long count = adminMapper.getCount(adminSearchBo);
        // 给分页模型对象·赋值
        // 给分页模型的总记录数赋值，给总记录数赋值的时候也会给总页数赋值
        pageBo.setResultCount(count);

        // 查询满足条件的员工列表
        List<Admin> admins = adminMapper.listByAdminSearchBo(adminSearchBo,pageBo);
        return admins;
    }

    @Transactional
    @Override
    public boolean saveOne(AdminAddBo adminAddBo) {
        //需要先调用security中的BCryptPasswordEncoder为新添加的用户生成密码
        //默认新用户密码为abc123
        adminAddBo.setAdminPass(new BCryptPasswordEncoder().encode("abc123"));
        //调用持久层添加
        int insert = adminMapper.insert(adminAddBo);
        int b = adminMapper.batchInsertAdminRole(adminAddBo);

        return insert>=1 && b>=1 ;
    }

    @Override
    public Admin getAdminById(Integer id) {
        Admin adminById = adminMapper.getAdminById(id);
        return adminById;
    }

    /**
     * 更新员工信息
     * @param adminUpdateBo
     * @return
     */
    @Transactional
    @Override
    public boolean updateByAdminUpdateBo(AdminUpdateBo adminUpdateBo) {
        //先更新admin数据表
        int i = adminMapper.updateByAdminUpdateBo(adminUpdateBo);
        //修改用户-角色关系：思路（先把用户原有的角色关系删除，然后重新添加）
        //删除当前角色原有的角色关系
        int i2 = adminMapper.batchDeleteRoleByAdminId(adminUpdateBo);
        //重新添加员工角色
        AdminAddBo adminAddBo = new AdminAddBo();
        adminAddBo.setRoleList(adminUpdateBo.getRoleList());
        adminAddBo.setAdminId(adminUpdateBo.getAdminId());
        int i1 = adminMapper.batchInsertAdminRole(adminAddBo);
        return i >= 1 && i1 >= 1 && i2 >= 1;
    }

    @Transactional
    @Override
    public boolean deleteByAdminId(Integer adminId) {



        //先删除admin表中的数据
        int i = adminMapper.deleteById(adminId);
        //然后需要删除admin-role中的数据
        Map<String,Object> map = new HashMap<>();
        map.put("admin_id", adminId);
        int i1 = adminRoleMapper.deleteByMap(map);
        return i >= 1 && i1 >= 1;
    }

    @Transactional
    @Override
    public boolean deleteByIdList(Integer[] idList) {
        //先删除admin表中的数据
        int i = adminMapper.deleteBatchIds(Arrays.asList(idList));
        //再删除admin-role关系
        int i1 = adminRoleMapper.deleteByAdminIds(idList);

        // 如果admin和admin_role全部删除成功再返回true
        return i >= 1 && i1 >= 1;
    }


}
