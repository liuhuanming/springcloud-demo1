package com.springcloud.netty;

import com.springcloud.netty.Dao.FileDao;
import com.springcloud.netty.Entity.FileEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NettyApplicationTests {
    @Autowired
    FileDao dao;

    @Test
    public void contextLoads() {
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName("测试");
        fileEntity.setFileUrl("C://");
        FileEntity save = dao.save(fileEntity);
        System.out.printf("save" + save);
    }

}
