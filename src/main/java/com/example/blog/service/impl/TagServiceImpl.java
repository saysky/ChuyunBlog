package com.example.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Tag;
import com.example.blog.mapper.PostTagRefMapper;
import com.example.blog.mapper.TagMapper;
import com.example.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <pre>
 *     标签业务逻辑实现类
 * </pre>
 *
 * @author : saysky
 * @date : 2018/1/12
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private PostTagRefMapper postTagRefMapper;

    @Override
    public BaseMapper<Tag> getRepository() {
        return tagMapper;
    }


    @Override
    public QueryWrapper<Tag> getQueryWrapper(Tag tag) {
        //对指定字段查询
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        if (tag != null) {
            if (StrUtil.isNotBlank(tag.getTagName())) {
                queryWrapper.eq("tag_name", tag.getTagName());
            }
        }
        return queryWrapper;
    }

    @Override
    public List<Tag> findHotTags(Integer limit) {
        return tagMapper.findAllWithCount(limit);
    }

    @Override
    public Tag findTagByTagName(String tagName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tag_name", tagName);
        return tagMapper.selectOne(queryWrapper);
    }


    @Override
    public List<Tag> strListToTagList(String tagList) {
        String[] tags = tagList.split(",");
        List<Tag> tagsList = new ArrayList<>();
        for (String tag : tags) {
            Tag t = findTagByTagName(tag);
            Tag nt = null;
            if (null != t) {
                tagsList.add(t);
            } else {
                nt = new Tag();
                nt.setTagName(tag);
                tagsList.add(insert(nt));
            }
        }
        return tagsList;
    }

    @Override
    public String tagListToStr(List<Tag> tagList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tagList.size(); i++) {
            sb.append(tagList.get(i).getTagName()).append(",");
        }
        String result = sb.toString();
        if (result.endsWith(",")) {
            return result.substring(0, result.length() - 1);
        }
        return result;
    }


    @Override
    public List<Tag> findByPostId(Long postId) {
        List<Tag> tagList = tagMapper.findByPostId(postId);
        return tagList;
    }

    @Override
    public Tag insert(Tag entity) {
        Tag isExist = findTagByTagName(entity.getTagName());
        if (isExist != null) {
            return isExist;
        }
        tagMapper.insert(entity);
        return entity;
    }

    @Override
    public Tag update(Tag entity) {
        Tag isExist = findTagByTagName(entity.getTagName());
        if (isExist != null && !Objects.equals(isExist.getId(), entity.getId())) {
            return isExist;
        }
        tagMapper.updateById(entity);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        Tag tag = this.get(id);
        if (tag != null) {
            //1.删除标签和文章的关联
            postTagRefMapper.deleteByTagId(id);
            //2.删除标签
            tagMapper.deleteById(id);
        }
    }


    @Override
    public Tag insertOrUpdate(Tag entity) {
        if (entity.getId() == null) {
            insert(entity);
        } else {
            update(entity);
        }
        return entity;
    }

    @Override
    public Integer deleteByUserId(Long userId) {
        return tagMapper.deleteByUserId(userId);
    }

    @Override
    public List<Tag> getHotTags(String keywords, Integer limit) {
        return tagMapper.getHotTags(keywords, limit);
    }

}
