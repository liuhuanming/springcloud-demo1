package com.springcloud.common.util;

import java.util.UUID;

/**
 * @description:
 * @author: Administrator
 * @date: 2021-02-27 8:44
 */
public class UUIDUtil {
    /**
     * 生成32位UUID编码
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
