package com.springcloud.jpa.service.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springcloud.jpa.dao.UserDao;
import com.springcloud.jpa.dto.UserInfoDto;
import com.springcloud.jpa.entity.QRoleEntity;
import com.springcloud.jpa.entity.QUserEntity;
import com.springcloud.jpa.entity.UserEntity;
import com.springcloud.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 22:12
 */
@Service
public class UserServiceImpl implements UserService  {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserEntity> findAll(UserEntity userEntity) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        List<UserEntity> list = jpaQueryFactory.
                select(qUserEntity).from(qUserEntity).
                where(qUserEntity.account.like(userEntity.getAccount())).fetch();
        return list;
    }

    @Override
    public UserEntity findOne(UserEntity userEntity) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        UserEntity user = jpaQueryFactory.
                select(qUserEntity).from(qUserEntity).
                where(qUserEntity.account.eq(userEntity.getAccount())).fetchFirst();
        return user;
    }

    @Override
    public List<UserInfoDto> findUserInfo(UserEntity userEntity) {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        QRoleEntity qRoleEntity = QRoleEntity.roleEntity;
//        jpaQueryFactory.select()

        return null;
    }
}
