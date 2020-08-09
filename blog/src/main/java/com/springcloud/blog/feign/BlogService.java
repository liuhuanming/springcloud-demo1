package com.springcloud.blog.feign;

import com.springcloud.blog.entity.Blog;
import com.springcloud.blog.feign.impl.BlogServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "USER-SERVICE", path = "/user", fallback = BlogServiceImpl.class)
public interface BlogService {
    @GetMapping("/{userId}")
    String getBlogByUserId(@PathVariable("userId") Integer userId);
}
