package com.chris.controller;

import com.chris.entity.User;
import com.chris.entity.bo.PageBo;
import com.chris.entity.bo.UserSearchBo;
import com.chris.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/admin")
    public String admin(UserSearchBo userSearchBo, PageBo pageBo, Model model){

        List<User> users = userService.listByUserSearchBo(userSearchBo, pageBo);
        model.addAttribute("userList" ,users);
        model.addAttribute("pageBo",pageBo);
        model.addAttribute("userSearchBo",userSearchBo);
        return "/user/admin";
    }
}
