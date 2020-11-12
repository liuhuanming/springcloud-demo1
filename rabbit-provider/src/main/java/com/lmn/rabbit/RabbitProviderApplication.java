package com.lmn.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableSwagger2 //api地址：http://localhost:8803/swagger-ui.html
public class RabbitProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProviderApplication.class, args);
    }

}
