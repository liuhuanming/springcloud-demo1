package com.lmn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:20
 */
@Data
@TableName(value = "test")
public class Test {
    private long id;
    private String name;
    private List<Content> contentList;
}
