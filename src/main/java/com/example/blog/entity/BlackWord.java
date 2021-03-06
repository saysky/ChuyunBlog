package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.common.base.BaseEntity;
import lombok.Data;

/**
 * 黑名单词语
 * @author 言曌
 * @date 2020/4/4 10:02 上午
 */
@Data
@TableName("black_word")
public class BlackWord extends BaseEntity {

    /**
     * 内容
     */
    private String content;
}
