package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;
import com.springcloud.osgi.factory.SigarFactory;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 23:23
 */
public class DiskUtil {
    public static List<SigarInfoEntity> getFileInfos() throws SigarException {
        List<SigarInfoEntity> fileInfoList = new ArrayList<SigarInfoEntity>();
        Sigar sigar = SigarFactory.getInstance();
        FileSystem[] fliest = sigar.getFileSystemList();

        for (int i = 0; i < fliest.length; i++) {
            FileSystem fs = fliest[i];
            fileInfoList.add(new SigarInfoEntity(i + "", "分区的盘符号" + i));
            fileInfoList.add(new SigarInfoEntity(fs.getDevName(), "盘符名称" + i));
            fileInfoList.add(new SigarInfoEntity(fs.getDirName(), "盘符路径" + i));
            fileInfoList
                    .add(new SigarInfoEntity(fs.getFlags() + "", "盘符标志" + i));
            fileInfoList.add(new SigarInfoEntity(fs.getSysTypeName(),
                    "盘符类型(FAT32,NTFS)" + i));
            fileInfoList
                    .add(new SigarInfoEntity(fs.getTypeName(), "盘符类型名" + i));
            fileInfoList.add(new SigarInfoEntity(fs.getType() + "", "盘符文件系统类型"
                    + i));

            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            switch (fs.getType()) {
                // TYPE_LOCAL_DISK : 本地硬盘
                case 2:
                    fileInfoList.add(new SigarInfoEntity(usage.getTotal() + "KB",
                            "文件系统总大小" + fs.getDevName()));
                    fileInfoList.add(new SigarInfoEntity(usage.getFree() + "KB",
                            "文件系统剩余大小" + fs.getDevName()));
                    fileInfoList.add(new SigarInfoEntity(usage.getAvail() + "KB",
                            "文件系统可用大小" + fs.getDevName()));
                    fileInfoList.add(new SigarInfoEntity(usage.getUsed() + "KB",
                            "文件系统已经使用量" + fs.getDevName()));
                    fileInfoList.add(new SigarInfoEntity(usage.getUsePercent()
                            * 100D + "%", "文件系统资源的利用率" + fs.getDevName()));
                    break;
                default:
                    break;
            }

            fileInfoList.add(new SigarInfoEntity(usage.getDiskReads() + "", fs
                    .getDevName() + "读出"));
            fileInfoList.add(new SigarInfoEntity(usage.getDiskWrites() + "", fs
                    .getDevName() + "写入"));
        }
        return fileInfoList;
    }

    public static void main(String[] args) throws SigarException {
        getFileInfos().stream().forEach(l -> {
            System.out.println(l.getName() + ">>>>" + l.getValue());
        });
    }
}
