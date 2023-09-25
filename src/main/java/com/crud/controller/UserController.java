package com.crud.controller;

import com.crud.controller.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("")
    public  String login(){
        return "redirect:pages/books.html";
    }
    @RequestMapping("/toError")
    @ResponseBody
    public R error(){
        return new R(false, "登录错误");
    }
}
