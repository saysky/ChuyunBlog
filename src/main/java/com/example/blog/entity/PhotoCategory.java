package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.common.base.BaseEntity;
import lombok.Data;

/**
 * 相册
 *
 * @author 言曌
 * @date 2021/3/2 2:02 下午
 */
@Data
@TableName("photo_category")
public class PhotoCategory extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 分类名称
     */
    private String cateName;

    /**
     * 分类排序号
     */
    private Integer cateSort;

    /**
     * 分类描述
     */
    private String cateDesc;
}
