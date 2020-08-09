package com.springcloud.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-04 00:03
 */
public class CalcUtil {
    private static DecimalFormat decimalFormat = new DecimalFormat("##.##");
    private static DecimalFormat decimalFormatRate = new DecimalFormat("##.##%");

    // 保留两个小数并四舍五入
    public static String getRate(double m, double n) {
        String rate = decimalFormat.format(m / n);
        return rate;
    }

    // 保留两个小数并四舍五入
    public static Double getDoubleRate(double m, double n) {
        String rate = decimalFormat.format(m / n);
        return Double.parseDouble(rate);
    }

    // 保留两个小数并四舍五入
    public static Double getRate(double m) {
        String rate = decimalFormat.format(m);
        return Double.parseDouble(rate);
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     *
     * @param bytes
     * @return
     */
    public static int getByteTo2Kb(int bytes) {
        BigDecimal fireside = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = fireside.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (int) returnValue;
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fireside.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (int) Math.ceil(returnValue);
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     *
     * @param bytes
     * @return
     */
    public static int getByteTo2Kb(Double bytes) {
        BigDecimal fireside = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = fireside.divide(megabyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        if (returnValue > 1)
            return (int) returnValue;
        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = fireside.divide(kilobyte, 2, BigDecimal.ROUND_UP)
                .floatValue();
        return (int) Math.ceil(returnValue);
    }

    public static void main(String[] args) {
        System.out.println(getByteTo2Kb(1024));
    }
}
