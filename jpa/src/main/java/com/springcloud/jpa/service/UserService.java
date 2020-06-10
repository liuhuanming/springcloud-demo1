package com.springcloud.jpa.service;

import com.springcloud.jpa.dto.UserInfoDto;
import com.springcloud.jpa.entity.UserEntity;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2020-05-31 22:11
 */
public interface UserService {
    List<UserEntity> findAll(UserEntity userEntity);
    UserEntity findOne(UserEntity userEntity);
    List<UserInfoDto> findUserInfo(UserEntity userEntity);
}
