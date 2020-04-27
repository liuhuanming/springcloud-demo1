package com.springcloud.quartz.controller;

import com.springcloud.configuration.result.Response;
import com.springcloud.configuration.result.Result;
import com.springcloud.quartz.service.QuartzService;
import com.springcloud.quartz.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 23:20
 */
@Api(value = "quartz",tags = "quartz 定时任务")
@RequestMapping("/quartz")
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private QuartzService quartzService;

    @ApiOperation(value = "查询全部")
    @GetMapping("")
    public Result test(){
        return Response.ok(testService.findAll());
    }

    @ApiOperation(value ="测试执行定时任务")
    @PostMapping("")
    public Result add(Class<? extends QuartzJobBean> job, String jobName, String jobGroupName, String jobTime) {
        Map map = new HashMap(2);
        map.put("id",1L);
        quartzService.addJob(job, jobName, jobGroupName, jobTime, map);
        return Response.ok();
    }

    @ApiOperation(value ="查询执行的定时任务")
    @GetMapping("/run")
    public Result findAllRunning() {
        return Response.ok(quartzService.queryAllJob());
    }
}
