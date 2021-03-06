package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.common.base.BaseEntity;
import com.example.blog.util.RelativeDateFormat;
import lombok.Data;

import java.util.List;

/**
 * 回复
 */
@Data
@TableName("comment")
public class Comment extends BaseEntity {

    /**
     * 文章ID
     */
    private Long postId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 回复内容
     */
    private String commentContent;

    /**
     * 上一级
     */
    private Long commentParent = 0L;

    /**
     * 关系路径
     */
    private String pathTrace;

    /**
     * 接受者用户Id
     */
    private Long acceptUserId;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 点踩数
     */
    private Long dislikeCount;

    /**
     * 回复文章
     */
    @TableField(exist = false)
    private Post post;


    /**
     * 回复人
     */
    @TableField(exist = false)
    private User user;


    /**
     * 当前回复下的所有子回复
     */
    @TableField(exist = false)
    private List<Comment> childComments;

    /**
     * 创建时间
     */
    @TableField(exist = false)
    private String createTimeStr;


    public String getCreateTimeStr() {
        return RelativeDateFormat.format(getCreateTime());
    }
}
