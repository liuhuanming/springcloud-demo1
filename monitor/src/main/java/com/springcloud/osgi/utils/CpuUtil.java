package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;
import com.springcloud.osgi.factory.SigarFactory;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 07:46
 */
public class CpuUtil {
    /**
     * 2.获取cpu信息
     *
     * @return
     * @throws SigarException
     */
    public static List<SigarInfoEntity> getCpuInfos() throws SigarException {
        List<SigarInfoEntity> cpuInfoList = new ArrayList<SigarInfoEntity>();
        Sigar sigar = SigarFactory.getInstance();
        CpuInfo[] cpuInfos = sigar.getCpuInfoList();
        CpuPerc[] cpuPercs = sigar.getCpuPercList();

        for (int i = 0; i < cpuInfos.length; i++) {
            Map<String, Object> cpuInfo = new ConcurrentHashMap<>();
            cpuInfo.put("第" + i + "个CPU的频率", cpuInfos[i].getMhz() + "MHz");
            cpuInfo.put("第" + i + "个CPU的类别", cpuInfos[i].getModel());
            cpuInfo.put("第" + i + "个CPU的供应商", cpuInfos[i].getVendor());
            cpuInfo.put("第" + i + "个CPU的供应商", cpuInfos[i].getVendor());
            cpuInfo.put("第" + i + "个CPU的缓冲存储器数量", cpuInfos[i].getCacheSize());
            cpuInfo.put("第" + i + "个CPU的核心数", cpuInfos[i].getTotalCores());
            cpuInfo.put("第" + i + "个CPU的缓冲存储器数量", cpuInfos[i].getCoresPerSocket());
            System.out.println(cpuInfos[i].toMap());
        }

        for (int i = 0; i < cpuPercs.length; i++) {
            CpuPerc cpuPerc = cpuPercs[i];
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(i), "第" + i
                    + "个CPU片段"));
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc
                    .getUser()), "CPU" + i + "用户使用率"));
            cpuInfoList.add(new SigarInfoEntity(
                    String.valueOf(cpuPerc.getSys()), "CPU" + i + "系统使用率"));
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc
                    .getWait()), "CPU" + i + "当前等待率"));
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc
                    .getNice()), "CPU" + i + "当前错误率"));
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc
                    .getIdle()), "CPU" + i + "当前空闲率"));
            cpuInfoList.add(new SigarInfoEntity(String.valueOf(cpuPerc
                    .getCombined()), "CPU" + i + "总的使用率"));

        }
        return cpuInfoList;
    }

    public static void main(String[] args) throws SigarException {
        System.out.println(getCpuInfos());
    }
}
