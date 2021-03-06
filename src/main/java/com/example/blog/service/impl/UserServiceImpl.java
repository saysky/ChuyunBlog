package com.example.blog.service.impl;

import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.blog.exception.MyBusinessException;
import com.example.blog.common.constant.CommonConstant;
import com.example.blog.entity.Role;
import com.example.blog.mapper.UserMapper;
import com.example.blog.entity.User;
import com.example.blog.enums.TrueFalseEnum;
import com.example.blog.service.CommentService;
import com.example.blog.service.PostService;
import com.example.blog.service.RoleService;
import com.example.blog.service.UserService;
import com.example.blog.util.Md5Util;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 用户业务逻辑实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Override
    public User findByUserName(String userName) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User findByEmail(String userEmail) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_email", userEmail);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public void updatePassword(Long userId, String password) {
        User user = new User();
        user.setId(userId);
        user.setUserPass(Md5Util.toMd5(password, CommonConstant.PASSWORD_SALT, 10));
        userMapper.updateById(user);
    }

    @Override
    public Page<User> findByRoleAndCondition(String roleName, User condition, Page<User> page) {
        List<User> users = new ArrayList<>();
        if (Objects.equals(roleName, CommonConstant.NONE)) {
            users = userMapper.findByCondition(condition, page);
        } else {
            Role role = roleService.findByRoleName(roleName);
            if (role != null) {
                users = userMapper.findByRoleIdAndCondition(role.getId(), condition, page);
            }
        }
        return page.setRecords(users);
    }

    /**
     * 修改禁用状态
     *
     * @param enable enable
     */
    @Override
    public void updateUserLoginEnable(User user, String enable) {
        //如果是修改为正常, 重置错误次数
        if (Objects.equals(TrueFalseEnum.TRUE.getValue(), enable)) {
            user.setLoginError(0);
        }
        user.setLoginEnable(enable);
        user.setLoginLast(new Date());
        userMapper.updateById(user);
    }


    /**
     * 增加登录错误次数
     *
     * @return 登录错误次数
     */
    @Override
    public Integer updateUserLoginError(User user) {
        user.setLoginError((user.getLoginError() == null ? 0 : user.getLoginError()) + 1);
        userMapper.updateById(user);
        return user.getLoginError();
    }

    /**
     * 修改用户的状态为正常
     *
     * @return User
     */
    @Override
    public User updateUserLoginNormal(User user) {
        user.setLoginEnable(TrueFalseEnum.TRUE.getValue());
        user.setLoginError(0);
        user.setLoginLast(new Date());
        userMapper.updateById(user);
        return user;
    }

    @Override
    public List<User> getHotUsers(Integer limit) {
        return userMapper.getHotUsers(limit);
    }

    @Override
    public List<User> searchUser(String keywords) {
        return userMapper.searchUser(keywords);
    }

    @Override
    public BaseMapper<User> getRepository() {
        return userMapper;
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(User user) {
        //对指定字段查询
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (user != null) {
            if (StrUtil.isNotBlank(user.getUserName())) {
                queryWrapper.eq("user_name", user.getUserName());
            }
            if (StrUtil.isNotBlank(user.getUserEmail())) {
                queryWrapper.eq("user_email", user.getUserEmail());
            }
        }
        return queryWrapper;
    }

    @Override
    public User insert(User user) {
        //1.验证表单数据是否合法
        basicUserCheck(user);
        //2.验证用户名和邮箱是否存在
        checkUserNameAndEmail(user);
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        //1.验证表单数据是否合法
        basicUserCheck(user);
        //2.验证用户名和邮箱是否存在
        checkUserNameAndEmail(user);
        userMapper.updateById(user);
        return user;
    }


    private void basicUserCheck(User user) {
        if (user.getUserName() == null || user.getUserEmail() == null || user.getUserDisplayName() == null) {
            throw new MyBusinessException("请输入完整信息!");
        }
        String userName = user.getUserName();
        userName = userName.trim().replaceAll(" ", "-");
        if (userName.length() < 4 || userName.length() > 20) {
            throw new MyBusinessException("用户名长度为4-20位!");
        }
        if (!Validator.isEmail(user.getUserEmail())) {
            throw new MyBusinessException("电子邮箱格式不合法!");
        }
        if (user.getUserDisplayName().length() < 1 || user.getUserDisplayName().length() > 20) {
            throw new MyBusinessException("昵称为2-20位");
        }
    }

    private void checkUserNameAndEmail(User user) {
        //验证用户名和邮箱是否存在
        if (user.getUserName() != null) {
            User nameCheck = findByUserName(user.getUserName());
            Boolean isExist = (user.getId() == null && nameCheck != null) ||
                    (user.getId() != null && nameCheck != null && !Objects.equals(nameCheck.getId(), user.getId()));
            if (isExist) {
                throw new MyBusinessException("用户名已经存在");
            }
        }
        if (user.getUserEmail() != null) {
            User emailCheck = findByEmail(user.getUserEmail());
            Boolean isExist = (user.getId() == null && emailCheck != null) ||
                    (user.getId() != null && emailCheck != null && !Objects.equals(emailCheck.getId(), user.getId()));
            if (isExist) {
                throw new MyBusinessException("电子邮箱已经存在");
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long userId) {
        //删除用户
        User user = get(userId);
        if (user != null) {
            //1.修改用户状态为已删除
            userMapper.deleteById(userId);
            //2.修改用户和角色关联
            roleService.deleteByUserId(userId);
            //3.删除其他
            postService.deleteByUserId(userId);
            commentService.deleteByUserId(userId);
        }
    }

    @Override
    public User insertOrUpdate(User user) {
        if (user.getId() == null) {
            user.setUserAvatar("/static/images/avatar/" + RandomUtils.nextInt(1, 41) + ".jpeg");
            insert(user);
        } else {
            update(user);
        }
        return user;
    }

    @Override
    public User get(Long id) {
        User user = userMapper.selectById(id);
        return user;
    }
}
