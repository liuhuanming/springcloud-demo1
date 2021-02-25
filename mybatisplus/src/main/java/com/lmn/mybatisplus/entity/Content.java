package com.lmn.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:21
 */
@Data
@TableName(value = "content")
public class Content {
    private long id;
    private String name;
}
