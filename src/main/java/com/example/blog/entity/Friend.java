package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.common.base.BaseEntity;
import lombok.Data;

/**
 * 好友
 * @author 言曌
 * @date 2021/3/5 2:34 下午
 */
@Data
@TableName("friend")
public class Friend extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 好友ID
     */
    private Long friendId;

    /**
     * 好友
     */
    @TableField(exist = false)
    private User friendUser;
}
