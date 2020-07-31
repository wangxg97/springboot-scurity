package com.security.springbootsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.liveconnect.SecurityContextHelper;

@RestController
public class LoginController {

    @RequestMapping(value = "/login-success")
    public String loginSuccess(){
        return "登录成功";
    }


    @GetMapping(value = "/r/r1")
    public  String r1(){
        return getUsername()+"访问资源1";
    }

    @GetMapping(value = "/r/r2")
    public  String r2(){
        return getUsername()+"访问资源2";
    }


    //获取当前用户信息
    private String getUsername(){
        String username="";

        //通过上下文 获取当前认证通过的用户身份
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //用户身份
        Object principal = authentication.getPrincipal();
        if (null ==principal){
            username="匿名";
        }
        if (principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails)principal;
            username=userDetails.getUsername();
        }
        return username;
    }
}
