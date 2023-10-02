package com.crud.controller;

import com.crud.controller.utils.R;
import com.crud.domain.Book;
import com.crud.domain.User;
import com.crud.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("")
    public  String login(){
        return "redirect:pages/books.html";
    }
    @RequestMapping("/toError")
    @ResponseBody
    public R error(){
        return new R(false, "登录错误");
    }
    @PutMapping("/changePassword")
    @ResponseBody
    public R changePassword(@RequestBody User user){
        int result = userService.changePassword(user);
        if(result==1){
           return   new R(true, "修改成功");
        }else {
            return   new R(false, "用户名不存在");
        }
    }
    @PostMapping("/register")
    @ResponseBody
    public R register(@RequestBody User user){
        int result = userService.register(user);
        if(result==1){
            return   new R(true, "注册成功");
        }else {
            return   new R(false, "用户名已存在");
        }
    }
}
