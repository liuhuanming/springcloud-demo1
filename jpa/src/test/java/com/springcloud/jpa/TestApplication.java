package com.springcloud.jpa;

import com.querydsl.core.Tuple;
import com.springcloud.jpa.entity.UserEntity;
import com.springcloud.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-06-09 22:56
 */
@RunWith(SpringRunner.class)
@MapperScan(basePackages = "com.springcloud.jpa.dao.*")
@SpringBootTest
public class TestApplication {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("测试");
        List<UserEntity> all = userService.findAll(userEntity);
        all.forEach(l -> {
            System.out.println(l.toString());
        });
    }

    @Test
    public void testUserInfo() {
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount("测试");
        List<Tuple> all = userService.findUserInfo(userEntity);
        all.forEach(l -> {
            System.out.println(l.toString());
        });
    }
}
