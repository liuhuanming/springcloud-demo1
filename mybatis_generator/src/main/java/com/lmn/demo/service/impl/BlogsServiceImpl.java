package com.lmn.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lmn.demo.dao.BlogsMapper;
import com.lmn.demo.entity.Blogs;
import com.lmn.demo.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: lmn
 * @date: 2019-10-24 21:51
 */
@Service
public class BlogsServiceImpl implements BlogsService {
    @Autowired
    private BlogsMapper blogsMapper;

    @Override
    public List<Blogs> queryAllBlogs() {
        return blogsMapper.queryAll();
    }

    @Override
    public PageInfo<Blogs> queryBlogsByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blogs> blogsList = blogsMapper.queryAll();
        //如果直接返回list，得到了分页的数据，如果添加下面步骤，返回pageInfo，则能得到包括list在内的分页信息
        PageInfo<Blogs> pageInfo = new PageInfo<>(blogsList);
        return pageInfo;
    }
}
