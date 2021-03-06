package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Photo;
import com.example.blog.mapper.PhotoMapper;
import com.example.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 言曌
 * @date 2021/3/2 5:42 下午
 */

@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Override
    public BaseMapper<Photo> getRepository() {
        return photoMapper;
    }

    @Override
    public QueryWrapper<Photo> getQueryWrapper(Photo photo) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (photo.getUserId() != null) {
            queryWrapper.eq("user_id", photo.getUserId());
        }
        if (photo.getCategoryId() != null) {
            queryWrapper.eq("category_id", photo.getCategoryId());
        }
        return queryWrapper;
    }

    @Override
    public Integer countByCategoryId(Long categoryId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("category_id", categoryId);
        return photoMapper.selectCount(queryWrapper);
    }
}
