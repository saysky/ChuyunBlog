package com.example.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.Comment;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <pre>
 *     回复业务逻辑实现类
 * </pre>
 *
 * @author : saysky
 * @date : 2018/1/22
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostService postService;


    @Override
    public BaseMapper<Comment> getRepository() {
        return commentMapper;
    }

    @Override
    public QueryWrapper<Comment> getQueryWrapper(Comment comment) {
        //对指定字段查询
        QueryWrapper<Comment> queryWrapper = new QueryWrapper<>();
        if (comment != null) {
            if (comment.getUserId() != null && comment.getUserId() != -1) {
                queryWrapper.eq("user_id", comment.getUserId());
            }
            if (comment.getAcceptUserId() != null && comment.getAcceptUserId() != -1) {
                queryWrapper.eq("accept_user_id", comment.getAcceptUserId());
            }
            if (StrUtil.isNotBlank(comment.getCommentContent())) {
                queryWrapper.like("comment_content", comment.getCommentContent());
            }
            if (comment.getPostId() != null && comment.getPostId() != -1) {
                queryWrapper.eq("post_id", comment.getPostId());
            }
        }
        return queryWrapper;
    }

    @Override
    public Integer deleteByUserId(Long userId) {
        return commentMapper.deleteByUserId(userId);
    }

    @Override
    public Integer deleteByAcceptUserId(Long acceptId) {
        return commentMapper.deleteByAcceptUserId(acceptId);
    }

    @Override
    public List<Comment> findByPostId(Long postId) {
        return commentMapper.findByPostId(postId);
    }


    @Override
    public Comment insert(Comment comment) {
        commentMapper.insert(comment);
        //修改文章回复数
        postService.resetCommentSize(comment.getPostId());
        return comment;
    }

    @Override
    public Comment update(Comment comment) {
        commentMapper.updateById(comment);
        //修改文章回复数
        postService.resetCommentSize(comment.getPostId());
        return comment;
    }

    @Override
    public void delete(Long commentId) {
        Comment comment = this.get(commentId);
        if (comment != null) {
            //1.删除回复
            commentMapper.deleteById(commentId);
            //2.修改文章的回复数量
            postService.resetCommentSize(comment.getPostId());
        }
    }

    @Override
    public Comment insertOrUpdate(Comment comment) {
        if(comment.getId() == null) {
            insert(comment);
        } else {
            update(comment);
        }
        return comment;
    }


}
