package com.example.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Friend;
import com.example.blog.mapper.FriendMapper;
import com.example.blog.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 言曌
 * @date 2021/3/5 2:41 下午
 */
@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendMapper friendMapper;

    @Override
    public BaseMapper<Friend> getRepository() {
        return friendMapper;
    }

    @Override
    public QueryWrapper<Friend> getQueryWrapper(Friend friend) {
        //对指定字段查询
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        if (friend != null) {
            if (friend.getUserId() != null && friend.getUserId() != -1) {
                queryWrapper.eq("user_id", friend.getUserId());
            }
            if (friend.getFriendId() != null && friend.getFriendId() != -1) {
                queryWrapper.eq("friend_id", friend.getFriendId());
            }
        }
        return queryWrapper;
    }

    @Override
    public Friend findByUserIdAndFriendId(Long userId, Long friendId) {
        QueryWrapper<Friend> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("friend_id", friendId);
        List<Friend> friends = friendMapper.selectList(queryWrapper);
        if (friends != null && friends.size() > 0) {
            return friends.get(0);
        }
        return null;
    }
}
