package com.shan.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shan.utils.JwtHelper;
import com.shan.utils.Result;
import com.shan.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @Author: Steven
 * @Date: 2024/8/12
 * @Time: 下午5:53
 * @Description:
 */
@Component
public class LoginProtectedInterceptor implements HandlerInterceptor {

    @Autowired
    JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        if(token == null || token.isEmpty() || jwtHelper.isExpiration(token)) {
            Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(result);
            response.getWriter().print(json);
            return false;
        }
        return true;
    }
}
