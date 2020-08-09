package com.springcloud.osgi.utils;

import com.springcloud.osgi.entity.SigarInfoEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-03 23:42/uujj
 */
public class JVMUtil {
    public synchronized static List<SigarInfoEntity> getJvmInfos() {
        List<SigarInfoEntity> jvmInfoList = new ArrayList<SigarInfoEntity>();
        Runtime r = Runtime.getRuntime();
        // 系统配置属性
        Properties sysProps = System.getProperties();
        jvmInfoList.add(new SigarInfoEntity("jvm处理器个数", String.valueOf(r.availableProcessors())));

        jvmInfoList.add(new SigarInfoEntity("Java的运行环境版本", sysProps.getProperty("java.version")));
        jvmInfoList.add(new SigarInfoEntity("Java的运行环境供应商", sysProps.getProperty("java.vendor")));

        jvmInfoList.add(new SigarInfoEntity("Java供应商的URL", sysProps.getProperty("java.vendor.url")));
        jvmInfoList.add(new SigarInfoEntity("Java的安装路径", sysProps.getProperty("java.home")));

        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机规范版本", sysProps.getProperty("java.vm.specification.version")));
        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机规范供应商", sysProps.getProperty("java.vm.specification.vendor")));
        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机规范名称", sysProps.getProperty("java.vm.specification.name")));

        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机实现版本", sysProps.getProperty("java.vm.version")));
        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机实现供应商", sysProps.getProperty("java.vm.vendor")));
        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机实现名称", sysProps.getProperty("java.vm.name")));

        jvmInfoList.add(new SigarInfoEntity("Java运行时环境规范版本", sysProps.getProperty("java.specification.version")));
        jvmInfoList.add(new SigarInfoEntity("Java运行时环境规范供应商", sysProps.getProperty("java.specification.vendor")));
        jvmInfoList.add(new SigarInfoEntity("Java的虚拟机规范名称", sysProps.getProperty("java.specification.name")));

        jvmInfoList.add(new SigarInfoEntity("Java的类格式版本号", sysProps.getProperty("java.class.version")));
        jvmInfoList.add(new SigarInfoEntity("Java的类路径", sysProps.getProperty("java.class.path")));

        jvmInfoList.add(new SigarInfoEntity("加载库时搜索的路径列表", sysProps.getProperty("java.library.path")));
        jvmInfoList.add(new SigarInfoEntity("默认的临时文件路径", sysProps.getProperty("java.io.tmpdir")));
        jvmInfoList.add(new SigarInfoEntity("一个或多个扩展目录的路径", sysProps.getProperty("java.ext.dirs")));

        return jvmInfoList;
    }

    public static void main(String[] args) {
        getJvmInfos().forEach(p -> {
            System.out.println(p.getName() + ">>" + p.getValue());
        });
    }
}
