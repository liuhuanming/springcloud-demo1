package com.springcloud.blog.controller;

import com.springcloud.blog.entity.Blog;
import com.springcloud.blog.feign.BlogService;
import com.springcloud.configuration.result.Response;
import com.springcloud.configuration.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-26 21:45
 */
@Api(value = "文章",tags = "文章博客模块")
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    BlogService blogService;

//    @ApiOperation(value = "根据title获取博客")
//    @GetMapping("/{title}")
//    public Result<List<Blog>> getBlogByTitle(@PathVariable("title") String title){
//        List<Blog> blogList = blogService.getBlogListByTitle(title);
//        return Response.ok(blogList);
//    }

    @ApiOperation(value = "根据userId获取博客")
    @GetMapping("/{userId}")
    public Result<String> getBlogByUserId(@PathVariable("userId") Integer userId){
        String info = blogService.getBlogByUserId(userId);
        return Response.ok("消费者收到信息："+ info);
    }
}
