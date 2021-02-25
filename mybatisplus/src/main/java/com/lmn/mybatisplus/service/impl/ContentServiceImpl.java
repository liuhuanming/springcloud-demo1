package com.lmn.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lmn.mybatisplus.dao.ContentDao;
import com.lmn.mybatisplus.entity.Content;
import com.lmn.mybatisplus.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:32
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Override
    public Content selectById(long id) {
        return contentDao.selectById(id);
    }

    @Override
    public List<Content> selectList(Wrapper<Content> wrapper) {
        return contentDao.selectList(wrapper);
    }
}
