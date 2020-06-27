package com.springcloud.thread.task;

import com.springcloud.thread.entity.ProductEntity;
import com.springcloud.thread.service.ProductService;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-27 22:48
 */
@Component
public class ProductTask {
    private final ProductService productService;

    public ProductTask(ProductService productService) {
        this.productService = productService;
    }

    public void run() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("女朋友");
        productEntity.setStock(1);
        productService.save(productEntity);
    }
}
