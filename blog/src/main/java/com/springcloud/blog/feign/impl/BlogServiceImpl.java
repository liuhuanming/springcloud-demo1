package com.springcloud.blog.feign.impl;

import com.springcloud.blog.feign.BlogService;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl implements BlogService {
    @Override
    public String getBlogByUserId(Integer userId) {
        return "消费者调用失败，启动熔断";
    }
}
