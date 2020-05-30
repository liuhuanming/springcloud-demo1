package com.springcloud.netty.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-17 20:49
 */
@Data
@Entity(name = "FileEntity")
@Table(name = "b_file_record")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String fileName;

    @Column(nullable = false, length = 500)
    private String fileUrl;
}
