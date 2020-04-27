package com.springcloud.quartz.dao;

import com.springcloud.quartz.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: Think
 * @date: 2020-04-14 08:05
 */
public interface TestDao extends JpaRepository<Test, Long> {

}
