package com.springcloud.common.util;

/**
 * @description:
 * @author: Administrator
 * @date: 2021-02-27 8:36
 */
public class ByteUtil {
    /**
     * 二进制转十六进制
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexStr = new StringBuilder();
        int num;
        for (byte aByte : bytes) {
            num = aByte;
            if (num < 0) {
                num += 256;
            }
            if (num < 16) {
                hexStr.append("0");
            }
            hexStr.append(Integer.toHexString(num));
        }
        return hexStr.toString().toUpperCase();
    }

}
