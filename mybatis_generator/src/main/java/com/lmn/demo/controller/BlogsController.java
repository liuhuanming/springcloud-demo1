package com.lmn.demo.controller;

import com.github.pagehelper.PageInfo;
import com.lmn.demo.common.result.Response;
import com.lmn.demo.common.result.Result;
import com.lmn.demo.entity.Blogs;
import com.lmn.demo.service.BlogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @date: 2019-10-24 21:52
 */
@Api(value = "博客", tags = "博客模块")
@RequestMapping("blogs")
@RestController
public class BlogsController {
    @Autowired
    private BlogsService blogsService;

    @ApiOperation(value = "获取所有的博客")
    @GetMapping(value = "/list")
    public Result<List<Blogs>> list() {
        List<Blogs> blogsList = blogsService.queryAllBlogs();
        return Response.ok(blogsList);
    }

    @ApiOperation(value = "分页获取所有的博客")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "第几页", required = true, paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "一页显示多少", required = true, paramType = "path"),
    })
    @GetMapping(value = "/page/{pageNum}/{pageSize}", produces = "application/json;charset=UTF-8")
    public Result<PageInfo<Blogs>> page(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        PageInfo<Blogs> list = blogsService.queryBlogsByPage(pageNum, pageSize);
        return Response.ok(list);
    }
}
