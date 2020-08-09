package com.springcloud.redis.controller;

import com.springcloud.common.result.Response;
import com.springcloud.common.result.Result;
import com.springcloud.redis.annotation.Limit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-05 21:24
 */
@Api(value = "redis模块", tags = "redis模块")
@RequestMapping("/redis")
@RestController
public class RedisController {
//    @Autowired
//    private RedisUtil redisUtil;

    @ApiOperation("测试")
    @GetMapping("/test")
    public Result test() {
        return Response.ok();
    }


    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    /**
     * 测试限流注解，下面配置说明该接口 60秒内最多只能访问 10次，保存到redis的键名为 limit_test，
     */
    @Limit(key = "test", period = 60, count = 10, name = "testLimit", prefix = "limit")
    @GetMapping("/testLimit")
    @ApiOperation("测试")
    public int testLimit() {
        return ATOMIC_INTEGER.incrementAndGet();
    }

//    @ApiOperation("读取")
//    @PutMapping("/key/{key}")
//    public Result getKey(@PathVariable String key) {
//        try {
//            Object o = redisUtil.get(key);
//            return Response.ok(o);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//        }
//    }
//
//    @ApiOperation("存储")
//    @GetMapping("/key/{key}/{value}")
//    public Result putKey(@PathVariable String key,@PathVariable String value) {
//        try {
//            Object o = redisUtil.rPush(key, value);
//            return Response.ok(o);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//        }
//    }
//
//    @ApiOperation("存储列表")
//    @PutMapping("/list/{key}")
//    public Result storeListKey(@PathVariable String key) {
//        List s = new ArrayList();
//        try {
//            for (int i = 0; i < 1000; i++) {
//                s.add("我是"+i);
//            }
//            return Response.ok(redisUtil.rPushAll(key, s));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//    @ApiOperation("获取列表")
//    @GetMapping("/list/{key}")
//    public Result getListKey(@PathVariable String key) {
//        try {
//            List<Object> list = redisUtil.lGet(key, 0, -1);
//            return Response.ok(list.toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//    @ApiOperation("存储hash")
//    @PutMapping("/hash/{key}")
//    public Result storeMapKey(@PathVariable String key) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("user01", "一件你不知道的事");
//        try {
//            redisUtil.hmset(key, map, 60);
//            return Response.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//    @ApiOperation("获取hash")
//    @GetMapping("/hash/{key}/{item}")
//    public Result getHastKey(@PathVariable String key, @PathVariable String item) {
//        try {
//            return Response.ok(redisUtil.hget(key, item));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//
//    @ApiOperation("存储有序集合")
//    @PutMapping("/sortSet/{key}")
//    public Result storeSortSetKey(@PathVariable String key) {
//        try {
//            redisUtil.sSet(key, "test001", "test002");
//            return Response.ok();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//    @ApiOperation("获取有序集合")
//    @GetMapping("/sortSet/{key}")
//    public Result getSortSetKey(@PathVariable String key) {
//        try {
//            return Response.ok(redisUtil.sGet(key));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
//
//    @ApiOperation("递增")
//    @GetMapping("/incr/{key}")
//    public Result getIncrKey(@PathVariable String key) {
//        try {
//            return Response.ok(redisUtil.incr(key, 1));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.error();
//
//        }
//    }
}
