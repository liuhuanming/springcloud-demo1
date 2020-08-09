package com.springcloud.quartz.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.quartz.service.QuartzService;
import com.springcloud.quartz.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 23:20
 */
@Api(value = "quartz", tags = "quartz 定时任务")
@RequestMapping("/quartz")
@RestController
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private QuartzService quartzService;

    @ApiOperation(value = "查询全部")
    @GetMapping("")
    public Result test() {
        return Response.ok(testService.findAll());
    }

    @ApiOperation(value = "添加测试执行定时任务")
    @PutMapping("")
    public Result add(String jobClassName, String jobName, String jobGroupName, String jobTime) {
        Map map = new HashMap(2);
        map.put("id", 1L);
        Class<? extends QuartzJobBean> jobClass = null;
        try {
            jobClass = (Class<? extends QuartzJobBean>) Class.forName(jobClassName);
            quartzService.addJob(jobClass, jobName, jobGroupName, jobTime, map);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Response.ok();
    }

    @ApiOperation(value = "修改测试执行定时任务")
    @PostMapping("")
    public Result updateJob(String jobClassName, String jobName, String jobGroupName, String jobTime) {
        Map map = new HashMap(2);
        map.put("id", 1L);
        Class<? extends QuartzJobBean> jobClass = null;
        quartzService.updateJob(jobName, jobGroupName, jobTime);
        return Response.ok();
    }

    @ApiOperation(value = "查询执行的定时任务")
    @GetMapping("/run")
    public Result findAllRunning() {
        return Response.ok(quartzService.queryAllJob());
    }
}
