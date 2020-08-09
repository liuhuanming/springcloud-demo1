package com.springcloud.quartz.service;

import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-21 07:09
 */
public interface QuartzService {
    void addJob(Class<? extends QuartzJobBean> job, String jobName, String jobGroupName, int jobTime,
                int jobTimes, Map<String, Object> jobParam);

    void addJob(Class<? extends QuartzJobBean> job, String jobName, String jobGroupName, String jobTime,
                Map<String, Object> jobParam);

    // 修改一个job的时间表达式
    void updateJob(String jobName, String jobGroupName, String jobTime);

    void deleteJob(String jobName, String jobGroupName);

    // 暂停
    void pauseJob(String jobName, String jobGroupName);

    //恢复job
    void resumeJob(String jobName, String jobGroupName);

    List<Map<String, Object>> queryAllJob();

    List<Map<String, Object>> queryRunJob();
}
