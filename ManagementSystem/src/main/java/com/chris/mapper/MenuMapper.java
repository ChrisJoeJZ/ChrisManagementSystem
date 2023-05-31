package com.chris.mapper;

import com.chris.entity.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据管理员id获取到这个管理员所能够访问的菜单列表
     * @param in
     * @return
     */
    List<Menu> listByAdminId(@Param("id") Integer in);
}
