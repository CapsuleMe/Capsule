package com.capsule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.capsule.service.OnlineUserService;


@Controller
public class UserController {

    @Autowired
    private OnlineUserService onlineUserService;
    
    @RequestMapping("/test")
    public @ResponseBody String test(){
        return "test1";
    }
}
