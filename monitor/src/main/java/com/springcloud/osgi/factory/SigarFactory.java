package com.springcloud.osgi.factory;

import org.hyperic.sigar.Sigar;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 07:49
 */
public class SigarFactory {
    /**
     * 初始化sigar
     */
    private static final Sigar SIGAR = new Sigar();

    private SigarFactory() {
        //防止初始化
    }

    /**
     * 线程安全获取sigar实例
     *
     * @return
     */
    public synchronized static Sigar getInstance() {

        return SIGAR;
    }
}
