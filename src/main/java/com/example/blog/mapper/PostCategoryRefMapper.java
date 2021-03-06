package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.PostCategoryRef;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author liuyanzhao
 */
@Mapper
public interface PostCategoryRefMapper extends BaseMapper<PostCategoryRef> {

    /**
     * 根据文章Id删除记录
     *
     * @param postId 文章Id
     * @return 影响行数
     */
    Integer deleteByPostId(Long postId);

    /**
     * 根据文章Id删除记录
     *
     * @param postIds 文章Id集合
     * @return 影响行数
     */
    Integer deleteByPostIds(List<Long> postIds);

    /**
     * 根据分类Id删除记录
     *
     * @param cateId 分类Id
     * @return 影响行数
     */
    Integer deleteByCateId(Long cateId);

    /**
     * 根据分类Id查询文章Id
     *
     * @param cateId 分类Id
     * @return 文章Id列表
     */
    List<Long> selectPostIdByCateId(Long cateId);

    /**
     * 根据文章Id查询分类Id
     *
     * @param postId 文章Id
     * @return 分类Id列表
     */
    List<Long> selectCateIdByPostId(Long postId);

    /**
     * 统计某篇分类的文章数
     *
     * @param cateId 分类Id
     * @return 文章Id列表
     */
    Integer countPostByCateId(Long cateId);
}

