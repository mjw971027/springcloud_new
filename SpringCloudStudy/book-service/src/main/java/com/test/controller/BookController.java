package com.test.controller;

import com.test.entity.Book;
import com.test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {
    private int port;
    @Resource
    BookService service;
    @Autowired
    Environment environment;

    /**
     * 当Application.yml中没有配置当前端口号，目前只能使用方式三获取端口
     *
     */
    public String getIp() {
        return environment.getProperty("local.server.port");
    }
    @RequestMapping("/book/{bid}")
    Book findBookById(@PathVariable("bid") int bid){
        System.out.println("调用用户服务");
        System.out.println(getIp());
        return service.getBookById(bid);
    }
}