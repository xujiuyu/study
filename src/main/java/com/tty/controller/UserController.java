package com.tty.controller;

import com.tty.annotation.SysLog;
import com.tty.entity.Result;
import com.tty.entity.User;
import com.tty.enumerate.Msg;
import com.tty.service.UserService;
import com.tty.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Tommy Yeung
 * @Date 2022/11/21 上午 10:39
 **/

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @SysLog("控制层: 访问管理员页面")
    @RequestMapping("/admin")
    public String adminPage(){
        return "admin";
    }

    @SysLog("控制层: 访问登录页面")
    @RequestMapping("/")
    public String loginPage(){
        return "login";
    }

    @SysLog("控制层: 登录校验")
    @PostMapping("/login")
    public String login(){
        return "index";
    }

    @SysLog("控制层: 访问首页")
    @GetMapping("/mainpage")
    public String index(){
        return "index";
    }

    @SysLog("控制层: 访问注册页面")
    @GetMapping("/regpage")
    public String regPage(){
        return "register";
    }

    @SysLog("控制层: 注册校验")
    @PostMapping("/reg")
    public String register(User user, Model model){

        //密码加密
        String md5Pw = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Pw);

        user.setSeparate(UrlUtils.generateSeparate(6));

        Boolean res = userService.register(user);

        if (res){
            model.addAttribute("regRes", new Result(res, Msg.REG_SUC));
            return "login";
        }

        return "register";

    }



}
