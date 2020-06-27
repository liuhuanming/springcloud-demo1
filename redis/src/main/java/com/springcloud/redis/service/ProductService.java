package com.springcloud.redis.service;

import com.springcloud.redis.dao.ProductDao;
import com.springcloud.redis.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 09:35
 */
@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;

    public ProductEntity getOneById(Integer id) {
        return productDao.getOne(id);
    }

    public ProductEntity updateOne(ProductEntity productEntity) {
        return productDao.save(productEntity);
    }
}
