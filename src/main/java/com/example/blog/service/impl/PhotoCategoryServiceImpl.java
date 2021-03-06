package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.PhotoCategory;
import com.example.blog.mapper.PhotoCategoryMapper;
import com.example.blog.service.PhotoCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 言曌
 * @date 2021/3/2 5:42 下午
 */

@Service
public class PhotoCategoryServiceImpl implements PhotoCategoryService {

    @Autowired
    private PhotoCategoryMapper photoCategoryMapper;

    @Override
    public BaseMapper<PhotoCategory> getRepository() {
        return photoCategoryMapper;
    }

    @Override
    public QueryWrapper<PhotoCategory> getQueryWrapper(PhotoCategory photoCategory) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (photoCategory.getUserId() != null) {
            queryWrapper.eq("user_id", photoCategory.getUserId());
        }
        return queryWrapper;
    }

    @Override
    public List<PhotoCategory> findByUserId(Long userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderByDesc("cate_sort");
        return photoCategoryMapper.selectList(queryWrapper);
    }
}
