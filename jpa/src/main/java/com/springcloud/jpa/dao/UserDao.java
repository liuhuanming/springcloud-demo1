package com.springcloud.jpa.dao;

import com.springcloud.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 09:49
 */
public interface UserDao extends JpaRepository<UserEntity, Long>, QuerydslPredicateExecutor<UserEntity> {
}
