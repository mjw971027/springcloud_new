package com.test.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.test.entity.User;
import com.test.entity.UserBorrowDetail;
import com.test.service.BorrowService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;

@RestController
public class BorrowController {

    @Resource
    BorrowService service;

    @RequestMapping("/borrow/{uid}")
    UserBorrowDetail findUserBorrows(@PathVariable("uid") int uid){
        return service.getUserBorrowDetailByUid(uid);
    }

    @RequestMapping("/test")
    @SentinelResource("test")   //注意这里需要添加@SentinelResource才可以，用户资源名称就使用这里定义的资源名称
    String findUserBorrows2(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "c",required = false) int c) {
        return "请求成功！a = "+a+", b = "+b+", c = "+c;
    }
    @RequestMapping("/borrow2/{uid}")
    UserBorrowDetail findUserBorrows2(@PathVariable("uid") int uid) throws InterruptedException {
        Thread.sleep(1000);
        return null;
    }
    @RequestMapping("/borrow3/{uid}")
    @SentinelResource(value = "findUserBorrows2", blockHandler = "test")
    UserBorrowDetail findUserBorrows3(@PathVariable("uid") int uid) {
        throw new RuntimeException();
    }

    UserBorrowDetail test(int uid, BlockException e){
        return new UserBorrowDetail(new User(), Collections.emptyList());
    }
}
