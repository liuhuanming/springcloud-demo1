package com.springcloud.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-11 23:20
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }
}
