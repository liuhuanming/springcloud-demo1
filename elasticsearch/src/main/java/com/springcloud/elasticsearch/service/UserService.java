package com.springcloud.elasticsearch.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.springcloud.elasticsearch.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int save(User user);

    User selectById(long id);

    List<User> selectList(@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
