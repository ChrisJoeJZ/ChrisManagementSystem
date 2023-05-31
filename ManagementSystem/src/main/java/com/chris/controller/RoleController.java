package com.chris.controller;

import com.chris.entity.Permission;
import com.chris.entity.Role;
import com.chris.entity.bo.PageBo;
import com.chris.service.PermissionService;
import com.chris.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/admin")
    public String admin(Role role, PageBo pageBo, Model model){

        List<Role> roles = roleService.listByRoleAndPageBo(role, pageBo);
        model.addAttribute("searchRole",role);
        model.addAttribute("roleList",roles);
        model.addAttribute("pageBo",pageBo);
        return "/role/admin";
    }



    @RequestMapping("/add")
    public String add(Model model){

        List<Permission> list = permissionService.list();
        model.addAttribute("permissionList",list);
        return "/role/add";
    }

    @RequestMapping("/validName")
    @ResponseBody
    public String validName(String roleName){
        Boolean roleNameExists = roleService.isRoleNameExists(roleName);
        return roleNameExists.toString();
    }

    @RequestMapping("/save")
    public String save(String roleName,Integer[] idList){
        boolean i = roleService.insert(roleName, idList);

        if(i){
            return "redirect:/index/success?message=role add success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role add error&controller=role/admin";
        }
    }

    @RequestMapping("/update")
    public String update(Integer roleId,Model model){

        model.addAttribute("role",roleService.getById(roleId));
        model.addAttribute("permissionList",permissionService.listVoByRoleId(roleId));

        return "/role/update";
    }

    @RequestMapping("alter")
    public String alter(Integer roleId,String roleName,Integer[] permissionIdList){
        boolean b = roleService.updateOne(roleId, roleName, permissionIdList);
        if(b){
            return "redirect:/index/success?message=role add success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role add error&controller=role/admin";
        }
    }


    @RequestMapping("/delete")
    public String delete(Integer roleId,Integer[] idList){

        boolean flag;
        //判断传过来的要删除的id是一个  还是集合
        if (roleId != null){
            flag = roleService.deleteById(roleId);
        }else {
            flag = roleService.deleteByRoleIdList(idList);
        }

        if(flag){
            return "redirect:/index/success?message=role delete success&controller=role/admin";
        }else {
            return "redirect:/index/error?message=role delete error&controller=role/admin";
        }


    }

}
