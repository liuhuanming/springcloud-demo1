package com.springcloud.elasticsearch.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private Date birthday;
    private String address;
    private String qq;
}
