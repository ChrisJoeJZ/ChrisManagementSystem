package com.chris.service;


import com.chris.entity.User;
import com.chris.entity.bo.PageBo;
import com.chris.entity.bo.UserSearchBo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 根据条件查询用户信息
     * @param userSearchBo
     * @param pageBo
     * @return
     */
    List<User> listByUserSearchBo(UserSearchBo userSearchBo, PageBo pageBo);


}
