package com.springcloud.thread.dao;

import com.springcloud.thread.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 09:24
 */
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

}
