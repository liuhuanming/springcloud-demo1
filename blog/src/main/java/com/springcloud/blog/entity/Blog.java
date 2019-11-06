package com.springcloud.blog.entity;

import lombok.Data;

@Data
public class Blog {
    private Integer id;
    private String title;
    private String des;
    private String context;
    private Integer userId;
}
