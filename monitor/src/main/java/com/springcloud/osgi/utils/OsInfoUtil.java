package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;
import org.hyperic.sigar.OperatingSystem;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 23:49
 */
public class OsInfoUtil {
    public static List<SigarInfoEntity> getOsInfos() {
        List<SigarInfoEntity> osInfoList = new ArrayList<SigarInfoEntity>();
        OperatingSystem os = OperatingSystem.getInstance();
        osInfoList.add(new SigarInfoEntity(os.getArch(), "操作系统"));
        osInfoList
                .add(new SigarInfoEntity(os.getCpuEndian(), "操作系统CpuEndian()"));
        osInfoList
                .add(new SigarInfoEntity(os.getDataModel(), "操作系统DataModel()"));
        osInfoList.add(new SigarInfoEntity(os.getDescription(), "操作系统的描述"));
        osInfoList.add(new SigarInfoEntity(os.getVendor(), "操作系统的供应商"));
        osInfoList
                .add(new SigarInfoEntity(os.getVendorCodeName(), "操作系统的供应商编号"));
        osInfoList.add(new SigarInfoEntity(os.getVendorName(), "操作系统的供应商名称"));
        osInfoList.add(new SigarInfoEntity(os.getVendorVersion(), "操作系统供应商类型"));
        osInfoList.add(new SigarInfoEntity(os.getVersion(), "操作系统的版本号"));
        return osInfoList;
    }

    public static void main(String[] args) {
        getOsInfos().forEach(o -> {
            System.out.println(o.getName() + ">>" + o.getValue());
        });
    }
}
