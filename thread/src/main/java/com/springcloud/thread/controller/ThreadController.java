package com.springcloud.thread.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.thread.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 21:24
 */
@Api(value = "thread模块", tags = "多线程模块")
@RequestMapping("/thread")
@RestController
public class ThreadController {
    @Autowired
    private ProductService productService;

    @ApiOperation("测试")
    @GetMapping
    public Result test() {
        return Response.ok(productService.findAll());
    }

}
