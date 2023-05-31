package com.chris.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chris.entity.Category;
import com.chris.entity.bo.CategoryAddBo;
import com.chris.entity.bo.CategorySearchBo;
import com.chris.entity.bo.PageBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

    /**
     * 根据条件查询出满足条件的category列表
     * @param categorySearchBo
     * @param pageBo
     * @return
     */
    List<Category> listByCategorySearchBo(@Param("categorySearchBo") CategorySearchBo categorySearchBo, @Param("pageBo") PageBo pageBo);

    /**
     * 根据条件查询出满足查询条件的总记录条数
     * @param categorySearchBo
     * @return
     */
    int getCount(@Param("categorySearchBo") CategorySearchBo categorySearchBo);

    /**
     * 验证添加的角色名是否已经存在
     * @param cateName
     * @return
     */

    /**
     * 根据分类名查询分类的方法
     * @param cateName
     * @return
     */
    Category getByCateName(@Param("cateName") String cateName);

    /**
     * 添加一个分类
     * @param category
     * @return
     */
    int insertOne(@Param("category") Category category);
}
