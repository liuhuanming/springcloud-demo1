package com.springcloud.quartz.service.impl;

import com.springcloud.quartz.service.QuartzService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 23:19
 */
@Service
public class QuartzServiceImpl implements QuartzService {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void startScheduler() {
        try {
//            scheduler.clear(); //防止一上来就执行
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param job
     * @param jobName
     * @param jobGroupName
     * @param jobTime 每隔多少秒
     * @param jobTimes 执行次数 0:不限制
     * @param jobParam
     */
    @Override
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, int jobTime, int jobTimes, Map<String, Object> jobParam) {
        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        // job参数
        if (jobParam!=null&&!jobParam.isEmpty()) {
            jobDetail.getJobDataMap().putAll(jobParam);
        }
        // 定义simpleTrigger规则
        Trigger trigger = null;
        if (jobTimes < 0) {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime)).startNow().build();
        } else {
            trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(1).withIntervalInSeconds(jobTime).withRepeatCount(jobTimes)).startNow().build();
        }
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 增加Job
     * @param job
     * @param jobName
     * @param jobGroupName
     * @param jobTime 时间表达式 （如：0/5 * * * * ? ）
     * @param jobParam
     */
    @Override
    public void addJob(Class<? extends QuartzJobBean> jobClass, String jobName, String jobGroupName, String jobTime, Map<String, Object> jobParam) {
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
        // 任务名称和组构成任务key
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
        // job参数
        if (jobParam!=null&&!jobParam.isEmpty()) {
            jobDetail.getJobDataMap().putAll(jobParam);
        }
        // 定义simpleTrigger规则
        Trigger trigger = null;
        // 定义调度触发规则
        // 使用cornTrigger规则
        // 触发器key
        trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroupName)
                .startAt(DateBuilder.futureDate(1, DateBuilder.IntervalUnit.SECOND))
                .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).startNow().build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改 一个job的 时间表达式
     * @param jobName
     * @param jobGroupName
     * @param jobTime
     */
    @Override
    public void updateJob(String jobName, String jobGroupName, String jobTime) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(jobTime)).build();
            // 重启触发器
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJob(String jobName, String jobGroupName) {
        try {
            scheduler.deleteJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 暂停job
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void pauseJob(String jobName, String jobGroupName) {
        try {
            scheduler.pauseJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 恢复job
     * @param jobName
     * @param jobGroupName
     */
    @Override
    public void resumeJob(String jobName, String jobGroupName) {
        try {
            scheduler.resumeJob(new JobKey(jobName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryAllJob() {
        List<Map<String, Object>> jobList = null;
        try {
            GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
            Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
            jobList = new ArrayList<Map<String, Object>>();
            for (JobKey jobKey : jobKeys) {
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                for (Trigger trigger : triggers) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jobName", jobKey.getName());
                    map.put("jobGroupName", jobKey.getGroup());
                    map.put("description", "触发器:" + trigger.getKey());
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                    map.put("jobStatus", triggerState.name());
                    if (trigger instanceof CronTrigger) {
                        CronTrigger cronTrigger = (CronTrigger) trigger;
                        String cronExpression = cronTrigger.getCronExpression();
                        map.put("jobTime", cronExpression);
                    }
                    jobList.add(map);
                }
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

    /**
     * 获取所有正在运行的job
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRunJob() {
        List<Map<String, Object>> jobList = null;
        try {
            List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
            jobList = new ArrayList<Map<String, Object>>(executingJobs.size());
            for (JobExecutionContext executingJob : executingJobs) {
                Map<String, Object> map = new HashMap<String, Object>();
                JobDetail jobDetail = executingJob.getJobDetail();
                JobKey jobKey = jobDetail.getKey();
                Trigger trigger = executingJob.getTrigger();
                map.put("jobName", jobKey.getName());
                map.put("jobGroupName", jobKey.getGroup());
                map.put("description", "触发器:" + trigger.getKey());
                Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
                map.put("jobStatus", triggerState.name());
                if (trigger instanceof CronTrigger) {
                    CronTrigger cronTrigger = (CronTrigger) trigger;
                    String cronExpression = cronTrigger.getCronExpression();
                    map.put("jobTime", cronExpression);
                }
                jobList.add(map);
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return jobList;
    }

}
