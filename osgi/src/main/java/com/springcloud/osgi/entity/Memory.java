package com.springcloud.osgi.entity;

import com.springcloud.configuration.util.CalcUtil;
import lombok.Setter;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-04 07:19
 */
public class Memory {
    /**
     * 内存总量
     */
    @Setter
    private double total;

    /**
     * 已用内存
     */
    @Setter
    private double used;

    /**
     * 剩余内存
     */
    @Setter
    private double free;

    public double getTotal() {
        return CalcUtil.getByteTo2Kb(total);
    }

    public double getUsed() {
        return CalcUtil.getByteTo2Kb(used);
    }

    public double getFree() {
        return CalcUtil.getByteTo2Kb(free);
    }

    public double getUsage() {
        return CalcUtil.getDoubleRate(used, total);
    }
}
