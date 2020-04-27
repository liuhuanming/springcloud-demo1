package com.springcloud.quartz;

import com.springcloud.quartz.job.TestJob1;
import com.springcloud.quartz.service.QuartzService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuartzApplicationTests {
	@Autowired
	private QuartzService quartzService;

	@Test
	public void contextLoads() {
		Map map = new HashMap<>(2);
		map.put("id", 1L);
		quartzService.addJob(TestJob1.class, "job", "test", "0/30 * * * * ?",map);

	}

}
