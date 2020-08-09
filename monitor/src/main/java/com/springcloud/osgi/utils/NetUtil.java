package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;
import com.springcloud.osgi.factory.SigarFactory;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 23:47
 */
public class NetUtil {
    public static List<SigarInfoEntity> getNetInfos() throws SigarException {
        List<SigarInfoEntity> netInfoList = new ArrayList<SigarInfoEntity>();
        Sigar sigar = SigarFactory.getInstance();
        String nIfNames[] = sigar.getNetInterfaceList();
        for (int i = 0; i < nIfNames.length; i++) {
            String name = nIfNames[i];
            NetInterfaceConfig nIfConfig = sigar.getNetInterfaceConfig(
                    name);

            netInfoList.add(new SigarInfoEntity(name, "网络设备名" + i));
            netInfoList.add(new SigarInfoEntity(nIfConfig.getAddress(), "IP地址"
                    + i));
            netInfoList.add(new SigarInfoEntity(nIfConfig.getNetmask(), "子网掩码"
                    + i));

            if ((nIfConfig.getFlags() & 1L) <= 0L) {
                System.out.println("getNetInterfaceStat not exist");
                continue;
            }
            NetInterfaceStat nIfStat = sigar.getNetInterfaceStat(name);
            netInfoList.add(new SigarInfoEntity(nIfStat.getRxPackets() + "",
                    "接收的总包裹数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getTxPackets() + "",
                    "发送的总包裹数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getRxBytes() + "",
                    "接收到的总字节数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getTxBytes() + "",
                    "发送的总字节数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getRxErrors() + "",
                    "接收到的错误包数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getTxErrors() + "",
                    "发送数据包时的错误数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getRxDropped() + "",
                    "接收时丢弃的包数" + i));
            netInfoList.add(new SigarInfoEntity(nIfStat.getTxDropped() + "",
                    "发送时丢弃的包数" + i));
        }
        return netInfoList;
    }

    public static void main(String[] args) throws SigarException {
        getNetInfos().forEach(n -> {
            System.out.println(n.getName() + ">>" + n.getValue());
        });
    }
}
