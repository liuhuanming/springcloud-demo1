package com.springcloud.osgi.entity;

import com.springcloud.configuration.util.CalcUtil;
import com.springcloud.configuration.util.CalendarUtils;
import lombok.Getter;
import lombok.Setter;

import java.lang.management.ManagementFactory;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-04 00:21
 */
public class JVM {
    /**
     * 当前JVM占用的内存总数(M)
     */
    @Setter
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    @Setter
    private double max;

    /**
     * JVM空闲内存(M)
     */
    @Setter
    private double free;

    /**
     * JDK版本
     */
    @Getter
    @Setter
    private String version;

    /**
     * JDK路径
     */
    @Getter
    @Setter
    private String home;

    public double getTotal() {
        return CalcUtil.getByteTo2Kb(total);
    }

    public double getMax() {
        return CalcUtil.getByteTo2Kb(max);
    }

    public double getFree() {
        return CalcUtil.getByteTo2Kb(free);
    }

    public double getUsed() {
        return CalcUtil.getByteTo2Kb(total - free);
    }

    public double getUsage() {
        return CalcUtil.getDoubleRate(total - free, total);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        return CalendarUtils.getDateTimeString();
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
//        return DateUtils.getDatePoor(DateUtils.getNowDate(), DateUtils.getServerStartDate());
        return CalendarUtils.getDateTimeString();
    }
}
