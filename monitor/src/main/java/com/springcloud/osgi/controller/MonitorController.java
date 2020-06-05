package com.springcloud.osgi.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 21:24
 */
@Api(value = "监控模块", tags = "监控模块")
@RequestMapping("/ monitor")
@RestController
public class MonitorController {

    @ApiOperation("测试")
    @GetMapping("")
    public Result test() {
        return Response.ok();
    }

}
