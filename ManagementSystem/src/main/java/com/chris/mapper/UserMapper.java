package com.chris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.User;
import com.chris.entity.bo.PageBo;
import com.chris.entity.bo.UserSearchBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户ID，用户昵称，用户手机号以及用户邮箱查询用户列表
     * @param userSearchBo
     * @param pageBo
     * @return
     */
    List<User> listByUserSearchBo(@Param("userSearchBo") UserSearchBo userSearchBo,@Param("pageBo") PageBo pageBo);

    /**
     * 根据查询条件，查询出满足条件的总记录条数
     * @param userSearchBo
     * @return
     */
    int getCount(@Param("userSearchBo") UserSearchBo userSearchBo);

}
