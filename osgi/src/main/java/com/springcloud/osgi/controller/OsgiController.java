package com.springcloud.osgi.controller;

import com.springcloud.configuration.result.Response;
import com.springcloud.configuration.result.Result;
import com.springcloud.osgi.service.OsgiService;
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
@Api(value = "监控模块", tags = "服务器监控模块")
@RequestMapping("/osgi")
@RestController
public class OsgiController {


    @ApiOperation("测试")
    @GetMapping("")
    public Result test() {
        return Response.ok();
    }

    @ApiOperation("获取服务器信息")
    @GetMapping("/server")
    public Result getServerInfo() throws Exception {
        OsgiService server = new OsgiService();
        server.copyTo();

        return Response.ok(server);
    }

}
