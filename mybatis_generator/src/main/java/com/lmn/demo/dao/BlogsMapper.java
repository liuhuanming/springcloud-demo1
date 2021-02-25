package com.lmn.demo.dao;

import com.lmn.demo.entity.Blogs;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BlogsMapper extends Mapper<Blogs> {
    List<Blogs> queryAll();
}