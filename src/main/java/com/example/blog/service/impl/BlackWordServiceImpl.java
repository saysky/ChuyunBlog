package com.example.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.blog.entity.BlackWord;
import com.example.blog.mapper.BlackWordMapper;
import com.example.blog.service.BlackWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 言曌
 * @date 2020/4/4 10:47 上午
 */
@Service
public class BlackWordServiceImpl implements BlackWordService {

    @Autowired
    private BlackWordMapper blackWordMapper;

    @Override
    public BaseMapper<BlackWord> getRepository() {
        return blackWordMapper;
    }

    @Override
    public QueryWrapper<BlackWord> getQueryWrapper(BlackWord blackWord) {
        //对指定字段查询
        QueryWrapper<BlackWord> queryWrapper = new QueryWrapper<>();
        if (blackWord != null) {
            if (StrUtil.isNotBlank(blackWord.getContent())) {
                queryWrapper.eq("content", blackWord.getContent());
            }
        }
        return queryWrapper;
    }
}
