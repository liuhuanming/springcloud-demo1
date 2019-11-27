package com.springcloud.elasticsearch.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.springcloud.elasticsearch.dao.UserMapper;
import com.springcloud.elasticsearch.entity.User;
import com.springcloud.elasticsearch.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectById(long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> selectList(Wrapper<User> queryWrapper) {
        return userMapper.selectList(queryWrapper);
    }
}
