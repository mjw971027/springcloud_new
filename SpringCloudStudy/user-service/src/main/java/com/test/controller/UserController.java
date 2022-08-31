package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService service;
    @RequestMapping("/blocked")
    JSONObject blocked(){
        JSONObject object = new JSONObject();
        object.put("code", 403);
        object.put("success", false);
        object.put("massage", "您的请求频率过快，请稍后再试！");
        return object;
    }
    @RequestMapping("/user/{uid}")
    public User findUserById(@PathVariable("uid") int uid){
        System.out.println("调用用户服务");
        return service.getUserById(uid);
    }
}
