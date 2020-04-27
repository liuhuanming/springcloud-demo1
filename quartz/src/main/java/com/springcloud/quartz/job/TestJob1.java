package com.springcloud.quartz.job;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-17 07:44
 */
@Component
public class TestJob1 extends QuartzJobBean {
    @Override
    public void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Hello world!:" + context.getJobDetail().getKey());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        System.out.println(">>>>>>" + jobDataMap.getKeys());
        System.out.println(">>>" + jobDataMap.entrySet());
    }
}
