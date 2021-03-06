package com.example.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyanzhao
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 查询前limit条回复
     *
     * @param limit 查询数量
     * @return 回复列表
     */
    List<Comment> findLatestCommentByLimit(Integer limit);

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     * @return 影响行数
     */
    Integer deleteByUserId(Long userId);

    /**
     * 根据用户Id删除
     *
     * @param userId 用户Id
     * @return 影响行数
     */
    Integer deleteByAcceptUserId(Long userId);

    /**
     * 获得某个ip用户最新的回复
     *
     * @param ip IP地址
     * @return 回复
     */
    Comment getLatestCommentByIP(String ip);

    /**
     * 获得子回复Id列表
     *
     * @param pathTrace 回复pathTrace封装
     * @return 回复Id列表
     */
    List<Long> selectChildCommentIds(@Param("pathTrace") String pathTrace);

    /**
     * 获得某个用户最新收到的回复
     *
     * @param userId 收到回复的用户
     * @param limit  查询数量
     * @return
     */
    List<Comment> getLatestCommentByAcceptUser(@Param("userId") Long userId,
                                               @Param("limit") Integer limit);

    /**
     * 根据文章ID获得评论列表
     * @param postId
     * @return
     */
    List<Comment> findByPostId(Long postId);
}

