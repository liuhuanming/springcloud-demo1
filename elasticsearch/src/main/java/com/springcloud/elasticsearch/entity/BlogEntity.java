package com.springcloud.elasticsearch.entity;

import lombok.Data;


@Data
public class BlogEntity {
    private String id;
    private String title;
    private String introduce;
    private String content;
    // 作者
    private String author;
}
