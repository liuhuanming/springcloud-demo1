package com.lmn.mybatisplus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.lmn.mybatisplus.entity.Content;

import java.util.List;

/**
 * @description:
 * @author: Think
 * @date: 2019-12-24 22:26
 */
public interface ContentService {
    Content selectById(long id);

    List<Content> selectList(Wrapper<Content> wrapper);
}
