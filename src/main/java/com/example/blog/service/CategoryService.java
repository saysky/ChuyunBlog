package com.example.blog.service;


import com.example.blog.common.base.BaseService;
import com.example.blog.entity.Category;

import java.util.List;

/**
 * <pre>
 *     分类业务逻辑接口
 * </pre>
 *
 * @author : saysky
 * @date : 2017/11/30
 */
public interface CategoryService extends BaseService<Category, Long> {

    /**
     * 查询所有分类目录,带count和根据level封装name
     *
     * @return 返回List集合
     */
    List<Category> findByUserId(Long userId);

    /**
     * 根据文章Id获得分类列表
     *
     * @param postId 文章id
     * @return 分类列表
     */
    Category findByPostId(Long postId);

    /**
     * 获得某个分类的所有文章数
     *
     * @param cateId 分类Id
     * @return 文章数
     */
    Integer countPostByCateId(Long cateId);

    /**
     * 根据用户ID删除
     *
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 将分类ID列表转成分类
     *
     * @param cateIds
     * @param userId
     * @return
     */
    List<Category> cateIdsToCateList(List<Long> cateIds, Long userId);
}
