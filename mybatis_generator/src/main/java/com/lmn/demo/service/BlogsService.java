package com.lmn.demo.service;

import com.github.pagehelper.PageInfo;
import com.lmn.demo.entity.Blogs;

import java.util.List;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-24 21:50
 */
public interface BlogsService {
    List<Blogs> queryAllBlogs();

    PageInfo<Blogs> queryBlogsByPage(int pageNum, int pageSize);
}
