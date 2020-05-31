package com.springcloud.jpa.service;

import com.springcloud.jpa.entity.UserEntity;
import org.apache.catalina.User;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 22:11
 */
public interface UserService {
    List<User> findAll(UserEntity userEntity);
}
