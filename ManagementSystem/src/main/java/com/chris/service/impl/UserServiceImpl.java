package com.chris.service.impl;

import com.chris.entity.User;
import com.chris.entity.bo.PageBo;
import com.chris.entity.bo.UserSearchBo;
import com.chris.mapper.UserMapper;
import com.chris.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> listByUserSearchBo(UserSearchBo userSearchBo, PageBo pageBo) {

        //先查询出满足条件的记录条数，赋值给pageBO
        int count = userMapper.getCount(userSearchBo);
        pageBo.setResultCount((long) count);

        //查询满足条件的客户信息
        List<User> users = userMapper.listByUserSearchBo(userSearchBo,pageBo);

        return users;
    }
}
