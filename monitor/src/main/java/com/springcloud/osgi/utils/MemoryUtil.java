package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;
import com.springcloud.osgi.factory.SigarFactory;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 23:45
 */
public class MemoryUtil {
    private static final double GB = 1024.0 * 1024.0 * 1024.0;

    public static List<SigarInfoEntity> getMemoryInfos() throws SigarException {
        List<SigarInfoEntity> memoryInfoList = new ArrayList<SigarInfoEntity>();
        Sigar sigar = SigarFactory.getInstance();
        Mem mem = sigar.getMem();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        memoryInfoList.add(new SigarInfoEntity(decimalFormat.format(mem.getTotal() / GB) + "G", "内存总量"));
        memoryInfoList.add(new SigarInfoEntity(
                decimalFormat.format(mem.getUsed() / GB) + "G", "当前内存使用量"));
        memoryInfoList.add(new SigarInfoEntity(
                decimalFormat.format(mem.getFree() / GB) + "G", "当前内存剩余量"));
        Swap swap = sigar.getSwap();
        memoryInfoList.add(new SigarInfoEntity(
                decimalFormat.format(swap.getTotal() / GB) + "G", "交换区总量"));
        memoryInfoList.add(new SigarInfoEntity(decimalFormat.format(swap.getUsed() / GB)
                + "G", "当前交换区使用量"));
        memoryInfoList.add(new SigarInfoEntity(decimalFormat.format(swap.getFree() / GB)
                + "G", "当前交换区剩余量"));
        return memoryInfoList;
    }

    public static void main(String[] args) throws SigarException {
        getMemoryInfos().forEach(m->{
            System.out.println(m.getName()+">>"+m.getValue());
        });
    }
}
