package com.lmn.mybatisplus.controller;

import com.lmn.mybatisplus.entity.Content;
import com.lmn.mybatisplus.entity.Test;
import com.lmn.mybatisplus.result.Response;
import com.lmn.mybatisplus.result.Result;
import com.lmn.mybatisplus.service.ContentService;
import com.lmn.mybatisplus.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-20 22:12
 */
@Api(value = "测试", tags = {"测试模块"})
@RestController
public class DemoController {
    @Autowired
    private TestService testService;
    @Autowired
    private ContentService contentService;

    @GetMapping("/test")
    @ApiOperation("content")
    public Result<Test> test(@RequestParam("id") long id) {
        Test test = testService.selectById(id);
        return Response.ok(test);
    }

    @GetMapping("/content")
    @ApiOperation("test")
    public Result<Content> content(@RequestParam("id") long id) {
        Content content = contentService.selectById(id);
        return Response.ok(content);
    }
}

