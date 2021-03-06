package com.example.blog.service;

import com.example.blog.common.base.BaseService;
import com.example.blog.entity.Tag;

import java.util.List;

/**
 * <pre>
 *     标签业务逻辑接口
 * </pre>
 *
 */
public interface TagService extends BaseService<Tag, Long> {


    /**
     * 热门标签
     *
     * @return 标签列表
     */
    List<Tag> findHotTags(Integer limit);

    /**
     * 根据标签名称查询
     *
     * @param tagName tagName
     * @return Tag
     */
    Tag findTagByTagName(String tagName);

    /**
     * 转换标签字符串为实体集合
     *
     * @param tagList tagList
     * @return List
     */
    List<Tag> strListToTagList(String tagList);

    /**
     * 标签列表转成字符串
     * @param tagList
     * @return
     */
    String tagListToStr(List<Tag> tagList);
    /**
     * 根据文章Id获得标签列表
     *
     * @param postId 文章id
     * @return 分类列表
     */
    List<Tag> findByPostId(Long postId);

    /**
     * 根据用户ID删除
     * @param userId
     * @return
     */
    Integer deleteByUserId(Long userId);

    /**
     * 热门标签
     * @param keywords
     * @return
     */
    List<Tag> getHotTags(String keywords, Integer limit);
}
