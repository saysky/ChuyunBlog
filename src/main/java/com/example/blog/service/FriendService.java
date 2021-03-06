package com.example.blog.service;

import com.example.blog.common.base.BaseService;
import com.example.blog.entity.Friend;

/**
 * @author 言曌
 * @date 2021/3/5 2:40 下午
 */

public interface FriendService extends BaseService<Friend, Long> {

    /**
     * 根据用户ID和被添加的ID查询
     *
     * @param userId
     * @param friendId
     * @return
     */
    Friend findByUserIdAndFriendId(Long userId, Long friendId);
}
