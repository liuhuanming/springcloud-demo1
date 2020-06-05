package com.springcloud.osgi.entity;

import com.springcloud.common.util.CalcUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-04 00:00
 */
public class CPU {
    /**
     * 核心数
     */
    @Getter
    @Setter
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    @Setter
    private double total;

    /**
     * CPU系统使用率
     */
    @Setter
    private double sys;

    /**
     * CPU用户使用率
     */
    @Setter
    private double used;

    /**
     * CPU当前等待率
     */
    @Setter
    private double wait;

    /**
     * CPU当前空闲率
     */
    @Setter
    private double free;


    public double getTotal() {
        return CalcUtil.getDoubleRate(total, 100);
    }

    public double getSys() {
        return CalcUtil.getDoubleRate(sys, total);
    }

    public double getUsed() {
        return CalcUtil.getDoubleRate(used,total);
    }

    public double getWait() {
        return CalcUtil.getDoubleRate(wait,total);
    }

    public double getFree() {
        return CalcUtil.getDoubleRate(free,total);
    }
}
