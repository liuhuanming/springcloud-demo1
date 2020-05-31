package com.springcloud.jpa.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springcloud.jpa.dao.UserDao;
import com.springcloud.jpa.entity.UserEntity;
import com.springcloud.jpa.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 22:12
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private JPAQueryFactory queryFactory;

    @PostConstruct  //指定初始化queryFactory
    public void init() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> findAll(UserEntity userEntity) {

        return null;
    }
}
