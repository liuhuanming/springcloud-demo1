package com.springcloud.thread;

import com.springcloud.common.spring.SpringBeanTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2
@Import(SpringBeanTools.class)
public class ThreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadApplication.class, args);
    }

}
