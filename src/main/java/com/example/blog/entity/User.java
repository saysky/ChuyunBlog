package com.example.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.example.blog.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@TableName("user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 显示名称
     */
    private String userDisplayName;

    /**
     * 密码
     */
    @JsonIgnore
    private String userPass;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    private String userEmail;

    /**
     * 头像
     */
    private String userAvatar;

    /**
     * 说明
     */
    private String userDesc;

    /**
     * 是否禁用登录
     */
//    @JsonIgnore
    private String loginEnable = "true";


    /**
     * 登录错误次数记录
     */
    private Integer loginError = 0;

    /**
     * 最后一次登录时间
     */
    private Date loginLast;

    /**
     * 0 正常
     * 1 禁用
     * 2 已删除
     */
    private Integer status = 0;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色名称
     */
    @TableField(exist = false)
    private String role;

    /**
     * 文章数
     */
    @TableField(exist = false)
    private Integer postCount;

}
