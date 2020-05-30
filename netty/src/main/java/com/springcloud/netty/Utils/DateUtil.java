package com.springcloud.netty.Utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-17 22:16
 */
public class DateUtil {
    private LocalDate localDate;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    public static void main(String[] args) {
        System.out.printf(getCurrentDateStr());
    }
}
