package com.example.blog.service;

import com.example.blog.entity.UserRoleRef;
import com.example.blog.common.base.BaseService;


public interface UserRoleRefService extends BaseService<UserRoleRef, Long> {

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     */
    void deleteByUserId(Long userId);


}
