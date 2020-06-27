package com.springcloud.redis;

import com.springcloud.redis.service.RushBuyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisApplicationTests {
	@Autowired
	private RushBuyService rushBuyService;
	// 抢购数量
	public static volatile Integer TOTAL = 100;

	@Test
	public void contextLoads() throws InterruptedException {
		rushBuyService.rushBuy(1);
	}

}
