package com.springcloud.thread.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 09:24
 */
@Data
@Entity
@Table(name = "product", schema = "test")
public class ProductEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "stock")
    private Integer stock;
}
