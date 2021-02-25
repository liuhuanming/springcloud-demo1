package com.lmn.demo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //设置允许跨域的路径
                .allowedOrigins("*") //设置允许跨域请求的域名
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE") //设置允许的方法
                .maxAge(3600)//跨域允许时间
                .allowCredentials(true); //是否允许证书 不再默认开启
    }
}
