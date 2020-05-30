package com.springcloud.netty.Dao;

import com.springcloud.netty.Entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-17 20:54
 */
public interface FileDao extends JpaRepository<FileEntity, Integer> {

}
