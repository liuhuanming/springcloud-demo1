package com.springcloud.thread.service;

import com.springcloud.thread.dao.ProductDao;
import com.springcloud.thread.entity.ProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ProductEntity> findAll() {
        return productDao.findAll();
    }
}
