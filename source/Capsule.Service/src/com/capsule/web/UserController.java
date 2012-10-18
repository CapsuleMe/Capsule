package com.capsule.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capsule.service.OnlineUserService;


@Controller
public class UserController {

    @Autowired
    private OnlineUserService onlineUserService;
    
    @RequestMapping("/test")
    public void test(){
        if(onlineUserService == null)
            System.out.println("------------");
    }
}
