package com.springcloud.redis.service;

import com.springcloud.redis.entity.ProductEntity;
import com.springcloud.redis.lock.RedisLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 抢购货品
 * @author: Think
 * @date: 2020-06-14 23:03
 */
@Service
@Slf4j
public class RushBuyService {
    // 库存
    private Integer total = 100;

    // 锁关键字
    private static String lock = "rush";

    @Autowired
    private RedisLockUtil redisLockUtil;

    @Autowired
    private ProductService productService;

//    /**
//     * 抢购货品
//     */
//    public void rushBuy() throws InterruptedException {
//        // redis锁开启
//        redisLockUtil.lock(lock, 10);
//        if (total > 0) { // 减抢购
//            total--;
//        } else {
//            log.info("----------货物卖完了！---------------");
//        }
//        // 模拟抢购货品
//        Thread.sleep(100);
//        log.info("===lock===减完库存后,当前库存===" + total);
//        //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
//        redisLockUtil.isHeldByCurrentThread(lock);
//    }

    /**
     * 抢购货品
     */
    public ProductEntity rushBuy(Integer id) throws InterruptedException {
        String key = "product_" + id;
        try {
            System.err.println(">>>>开始抢购");
            // 加锁，类似里ReentrantLock 可重入锁
            redisLockUtil.lock(key, 5);
            ProductEntity product = productService.getOneById(id);
            if (product.getStock() == 0) {
                System.err.println(">>货物" + product.getProductName() + "已经卖完了！！！");
            } else {
                product.setStock(product.getStock() - 1);
                System.err.println(">>货物数量"+product.getStock());
                ProductEntity productEntity = productService.updateOne(product);
                //如果该线程还持有该锁，那么释放该锁。如果该线程不持有该锁，说明该线程的锁已到过期时间，自动释放锁
                redisLockUtil.isHeldByCurrentThread(key);
                return  productEntity;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ProductEntity();
    }
}
