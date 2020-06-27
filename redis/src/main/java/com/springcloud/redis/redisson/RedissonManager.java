package com.springcloud.redis.redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-26 11:04
 */
@Configuration
public class RedissonManager {
    @Value("${redisson.address}")
    private String addressUrl;
    @Value("${redisson.password}")
    private String password;

    /**
     * 单机配置模式
     * @return
     * @throws Exception
     */
    @Bean
    public RedissonClient getRedisson() throws Exception{
        RedissonClient redisson = null;
        Config config = new Config();
        config.useSingleServer().setAddress(addressUrl)
                .setPassword(password)
                .setRetryInterval(3000)
                .setTimeout(10000)
                .setConnectTimeout(1000)
                .setConnectionPoolSize(1000);
        redisson = Redisson.create(config);
        System.out.println(redisson.getConfig().toYAML());
        return redisson;
    }

//    @Bean
//    public RedissonClient getRedisson() {
//        RedissonClient redisson = null;
//        Config config = new Config();
//        config.useMasterSlaveServers()
//                .setMasterAddress("redis://***(主服务器IP):6379").setPassword("lmnredis")
//                .addSlaveAddress("redis://***（从服务器IP）:6379")
//                .setReconnectionTimeout(10000)
//                .setRetryInterval(5000)
//                .setTimeout(10000)
//                .setConnectTimeout(10000);//（连接超时，单位：毫秒 默认值：3000）;
//        //  哨兵模式
//        //  config.useSentinelServers()
//        //  .setMasterName("mymaster").setPassword("lmnredis")
//        //  .addSentinelAddress("***(哨兵IP):26379", "***(哨兵IP):26379", "***(哨兵IP):26380");
//        redisson = Redisson.create(config);
//        return redisson;
//    }
}
