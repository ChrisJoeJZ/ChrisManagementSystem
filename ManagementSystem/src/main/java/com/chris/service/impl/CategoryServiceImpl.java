package com.chris.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chris.entity.Category;
import com.chris.entity.bo.CategoryAddBo;
import com.chris.entity.bo.CategorySearchBo;
import com.chris.entity.bo.PageBo;
import com.chris.mapper.CategoryMapper;
import com.chris.service.CategoryService;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    //根据条件查询出满足搜索条件的数据
    @Override
    public List<Category> listByCategorySearchBo(CategorySearchBo categorySearchBo, PageBo pageBo) {
        //先查询出满足条件的记录条数
        int count = categoryMapper.getCount(categorySearchBo);
        //给pageBo赋值
        pageBo.setResultCount((long) count);
        //再查询出满足条件的数据
        List<Category> categories = categoryMapper.listByCategorySearchBo(categorySearchBo, pageBo);
        return categories;
    }

    //查询出所有的分类
    @Override
    public List<Category> list() {
        List<Category> categories = categoryMapper.selectList(new QueryWrapper<Category>());
        return categories;
    }

    @Override
    public Category getByCateName(String cateName) {
        Category byCateName = categoryMapper.getByCateName(cateName);
        return byCateName;
    }

    @Override
    public boolean insertOne(CategoryAddBo categoryAddBo) {
        //创建一个Category对象
        Category category = new Category();
        category.setCateName(categoryAddBo.getCateName());
        category.setCateSort(categoryAddBo.getCateSort());
        category.setCateParentid(categoryAddBo.getCateParentid());
        LocalDateTime now = LocalDateTime.now();
        category.setCreatetime(now);
        category.setUpdatetime(now);
        int i = categoryMapper.insertOne(category);
        return i>=1;
    }


}
