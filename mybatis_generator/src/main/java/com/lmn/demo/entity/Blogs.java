package com.lmn.demo.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "blogs")
public class Blogs {
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String introduce;

    /**
     * 栏目id
     */
    @Column(name = "channel_id")
    private Long channelId;

    /**
     * 内容id
     */
    @Column(name = "content_id")
    private Long contentId;

    /**
     * 作者id
     */
    @Column(name = "author_id")
    private Long authorId;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Long typeId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 发布时间
     */
    @Column(name = "release_time")
    private Date releaseTime;

    /**
     * 发布ip
     */
    @Column(name = "release_ip")
    private String releaseIp;

    /**
     * 是否置顶 0：不置顶，其他：置顶及级别
     */
    private Long top;

    /**
     * 是否推荐 0：不推荐 1 推荐
     */
    private Long support;

    /**
     * 浏览量
     */
    private Long browse_count;

    /**
     * 点赞数
     */
    private Long praise_count;


}