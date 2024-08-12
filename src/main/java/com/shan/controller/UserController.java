package com.shan.controller;

import com.shan.pojo.User;
import com.shan.service.TypeService;
import com.shan.service.UserService;
import com.shan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Steven
 * @Date: 2024/8/9
 * @Time: 下午6:16
 * @Description:
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user) {
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token) {
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam("username") String username) {
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result regist(@RequestBody User user) {
        Result result = userService.regist(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token) {
        Result result = userService.checkLogin(token);
        return result;
    }

}
