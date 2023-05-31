package com.chris.controller;

import com.chris.entity.Admin;
import com.chris.entity.Role;
import com.chris.entity.bo.AdminAddBo;
import com.chris.entity.bo.AdminSearchBo;
import com.chris.entity.bo.AdminUpdateBo;
import com.chris.entity.bo.PageBo;
import com.chris.service.AdminService;
import com.chris.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @Resource
    private RoleService roleService;


    @RequestMapping("/delete")
    public String delete(Integer adminId,Integer[] idList){
        boolean result;
        if(adminId != null){
            result = adminService.deleteByAdminId(adminId);
        }else {
            result = adminService.deleteByIdList(idList);
        }

        if (result){
            return "redirect:/index/success?message=WellDone&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=What Are You Doing Bro&controller=admin/admin";
        }
    }

    @RequestMapping("/add")
    public String add(Model model){
        List<Role> list = roleService.list();
        model.addAttribute("roleList",list);
        return "admin/add";
    }

    @PostMapping("/save")
    public String save(AdminAddBo adminAddBo){
        boolean b = adminService.saveOne(adminAddBo);
        if (b){
            return "redirect:/index/success?message=WellDone&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=What Are You Doing Bro&controller=admin/admin";
        }
    }

    @RequestMapping("/admin")
    public String admin(AdminSearchBo adminSearchBo, PageBo pageBo, Model model){
        // 获取到了符合条件的员工列表，并且给分页模型赋值了
        List<Admin> admins = adminService.listBySearchBo(adminSearchBo, pageBo);

        // 将员工列表和分页模型全部放入请求域中
        model.addAttribute("adminList",admins);
        model.addAttribute("pageBo",pageBo);

        // 将查询条件放到请求域中，做·持久化
        model.addAttribute("searchBo",adminSearchBo);
        // 将所有的角色放到请求域中
        model.addAttribute("roleList",roleService.list());
        return "admin/admin";
    }

    @RequestMapping("/update")
    public String update(Integer adminId,Model model){
        Admin adminById = adminService.getAdminById(adminId);
        model.addAttribute("admin",adminById);
        model.addAttribute("roleList",roleService.list());
        return "/admin/update";
    }

    @RequestMapping("/alter")
    public String alter(AdminUpdateBo adminUpdateBo){
        boolean b = adminService.updateByAdminUpdateBo(adminUpdateBo);

        if (b){
            return "redirect:/index/success?message=WellDone&controller=admin/admin";
        }else {
            return "redirect:/index/error?message=What Are You Doing Bro&controller=admin/admin";
        }
    }
}
