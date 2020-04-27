package com.springcloud.quartz.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 08:00
 */
@Entity(name = "test")
@Table(name = "test")
@Data
public class Test {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;
}
