package com.shan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shan.pojo.User;
import com.shan.service.UserService;
import com.shan.mapper.UserMapper;
import com.shan.utils.JwtHelper;
import com.shan.utils.MD5Util;
import com.shan.utils.Result;
import com.shan.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Steven
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-08-09 17:31:17
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(queryWrapper);

        if(loginUser == null)
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        if(!loginUser.getUserPwd().equals(MD5Util.encrypt(user.getUserPwd()))) {
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
        }

        String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
        Map data = new HashMap<>();
        data.put("token", token);

        return Result.ok(data);
    }

    @Override
    public Result getUserInfo(String token) {
        //1.判定是否有效期
        if (jwtHelper.isExpiration(token)) {
            //true过期,直接返回未登录
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        Long userId = jwtHelper.getUserId(token);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUid, userId).select(User::getUid, User::getUsername, User::getNickName);
        User user = userMapper.selectOne(queryWrapper);

        if(user == null)
            return Result.build(null, ResultCodeEnum.NOTLOGIN);

        user.setUserPwd(null);
        Map data = new HashMap<>();
        data.put("loginUser", user);

        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        User user = userMapper.selectOne(queryWrapper);
        if(user != null)
            return Result.build(null, ResultCodeEnum.USERNAME_USED);

        return Result.ok(null);
    }

    @Override
    public Result regist(User user) {
        Result ifUsedName = checkUserName(user.getUsername());
        if(!ifUsedName.getCode().equals(200))
            return ifUsedName;
        user.setUserPwd(MD5Util.encrypt(user.getUsername()));
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result checkLogin(String token) {
        if(jwtHelper.isExpiration(token))
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        return Result.ok(null);
    }

}




