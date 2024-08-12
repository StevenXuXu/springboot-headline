package com.shan.service;

import com.shan.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shan.utils.Result;

/**
* @author Steven
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-08-09 17:31:17
*/
public interface UserService extends IService<User> {

    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result regist(User user);

    Result checkLogin(String token);
}
