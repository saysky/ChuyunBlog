package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Photo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 言曌
 * @date 2021/3/2 5:39 下午
 */
@Mapper
public interface PhotoMapper extends BaseMapper<Photo> {
}
