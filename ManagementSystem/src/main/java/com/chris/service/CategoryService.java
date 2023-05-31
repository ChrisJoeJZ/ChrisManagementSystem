package com.chris.service;

import com.chris.entity.Category;
import com.chris.entity.bo.CategoryAddBo;
import com.chris.entity.bo.CategorySearchBo;
import com.chris.entity.bo.PageBo;

import java.util.List;

public interface CategoryService {

    /**
     * 根据筛选条件查询出所有符合条件的数据
     * @param categorySearchBo
     * @param pageBo
     * @return
     */
    List<Category> listByCategorySearchBo(CategorySearchBo categorySearchBo, PageBo pageBo);

    /**
     * 拆卸拿出所有分类
     * @return
     */
    List<Category> list();

    /**
     * 通过分类名查询分类信息
     * @param cateName
     * @return
     */
    Category getByCateName(String cateName);

    /**
     * 添加分类
     * @param categoryAddBo
     * @return
     */
    boolean insertOne(CategoryAddBo categoryAddBo);
}
