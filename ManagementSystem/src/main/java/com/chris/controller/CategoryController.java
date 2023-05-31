package com.chris.controller;

import com.chris.entity.Category;
import com.chris.entity.bo.CategoryAddBo;
import com.chris.entity.bo.CategorySearchBo;
import com.chris.entity.bo.PageBo;
import com.chris.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @RequestMapping("/admin")
    public String admin(CategorySearchBo categorySearchBo, PageBo pageBo, Model model){
        model.addAttribute("categorySearchBo",categorySearchBo);
        model.addAttribute("pageBo",pageBo);
        //将满足条件的分类放到请求域中，返回给前端页面
        List<Category> categories = categoryService.listByCategorySearchBo(categorySearchBo, pageBo);
        model.addAttribute("categoryList",categories);
        model.addAttribute("categoryAll",categoryService.list());
        return "/category/admin";
    }

    @RequestMapping("/add")
    public String add(Model model){

        //先查询出所有分类，添加到请求域中,共父级分类选择
        List<Category> list = categoryService.list();
        model.addAttribute("cateList",list);

        return "/category/add";
    }

    @RequestMapping("/validName")
    @ResponseBody
    public String validName(String cateName){
        Category byCateName = categoryService.getByCateName(cateName);

        return byCateName == null? "true":"false";
    }

    /**
     * 通过categoryAddBo添加新的分类
     * @return
     */
    @RequestMapping("/save")
    public String save(CategoryAddBo categoryAddBo){
        boolean b = categoryService.insertOne(categoryAddBo);
        if(b){
            return "redirect:/index/success?message=category add success&controller=category/admin";
        }else {
            return "redirect:/index/error?message=category add error&controller=category/admin";
        }

    }

    @RequestMapping("/update")
    public String update(){
        return "/category/update";
    }

}
