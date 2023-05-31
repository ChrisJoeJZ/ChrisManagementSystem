package com.chris;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chris.entity.Permission;
import com.chris.entity.Role;
import com.chris.entity.vo.PermissionWithRoleVo;
import com.chris.mapper.PermissionMapper;
import com.chris.mapper.UserMapper;
import com.chris.service.PermissionService;
import com.chris.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Test
    public void roleServiceTest(){
//        List<Role> list = roleService.list();
//        for (Role role : list) {
//            log.info(role.toString());
//        }
        Role byId = roleService.getById(1);
        log.info(byId.toString());

    }

    @Resource
    private PermissionService permissionService;

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void PermissionServiceTest(){
//        List<Permission> permissions = permissionMapper.selectList(new QueryWrapper<Permission>());
//        log.info(permissions.toString());

        List<PermissionWithRoleVo> list = permissionService.listVoByRoleId(1);
        for (PermissionWithRoleVo permissionWithRoleVo : list) {
            log.info(permissionWithRoleVo.toString());
        }
    }


}
