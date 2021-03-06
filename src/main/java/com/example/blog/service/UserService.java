package com.example.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.common.base.BaseService;
import com.example.blog.entity.User;

import java.util.List;

/**
 * 用户业务逻辑接口
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 根据用户名获得用户
     *
     * @param userName 用户名
     * @return 用户
     */
    User findByUserName(String userName);


    /**
     * 根据邮箱查找用户
     *
     * @param userEmail 邮箱
     * @return User
     */
    User findByEmail(String userEmail);

    /**
     * 更新密码
     *
     * @param userId   用户Id
     * @param password 密码
     */
    void updatePassword(Long userId, String password);

    /**
     * 分页获取所有用户
     *
     * @param roleName  角色名称
     * @param condition 查询条件
     * @param page      分页信息
     * @return 用户列表
     */
    Page<User> findByRoleAndCondition(String roleName, User condition, Page<User> page);


    /**
     * 修改禁用状态
     *
     * @param enable enable
     */
    void updateUserLoginEnable(User user, String enable);

    /**
     * 增加登录错误次数
     *
     * @return 登录错误次数
     */
    Integer updateUserLoginError(User user);

    /**
     * 修改用户的登录状态为正常
     *
     * @return User
     */
    User updateUserLoginNormal(User user);


    /**
     * 获得热门用户
     *
     * @param limit 用户数量
     * @return
     */
    List<User> getHotUsers(Integer limit);

    /**
     * 搜索用户
     *
     * @param keywords
     * @return
     */
    List<User> searchUser(String keywords);

}
