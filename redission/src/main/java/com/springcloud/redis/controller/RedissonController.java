package com.springcloud.redis.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.redis.entity.ProductEntity;
import com.springcloud.redis.service.RushBuyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 21:24
 */
@Api(value = "分布式锁模块", tags = "redisson模块")
@RequestMapping("/redisson")
@RestController
public class RedissonController {
    @Autowired
    private RushBuyService rushBuyService;

    /**
     * 模拟这个是商品库存
     */
    public static volatile Integer TOTAL = 10;

    @ApiOperation("测试")
    @GetMapping("/rush1")
    public Result lockDecreaseStock() throws InterruptedException {
        String s = rushBuyService.rushBuy1();
        return Response.ok(s);
    }

    @ApiOperation("测试注解")
    @GetMapping("/rush2/{id}")
    public Result annotationLock(@PathVariable Integer id) throws InterruptedException {
        String s = rushBuyService.rushBuy2(id);
        return Response.ok(s);
    }

    @ApiOperation("测试")
    @GetMapping("/product/{id}")
    public Result test(@PathVariable Integer id) throws InterruptedException {
        ProductEntity productEntity = rushBuyService.rushBuy(id);
        if (productEntity == null) {
            return Response.ok("很遗憾，您没有抢购成功！");
        }
        return Response.ok(productEntity);
    }

}
