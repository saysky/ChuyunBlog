package com.example.blog.service;

import com.example.blog.common.base.BaseService;
import com.example.blog.entity.Photo;

/**
 * @author 言曌
 * @date 2021/3/2 5:40 下午
 */
public interface PhotoService extends BaseService<Photo, Long> {

    /**
     * 根据分类ID查询照片数量
     *
     * @param categoryId
     * @return
     */
    Integer countByCategoryId(Long categoryId);
}
