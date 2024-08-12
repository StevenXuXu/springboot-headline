package com.shan.config;

import com.shan.interceptor.LoginProtectedInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Steven
 * @Date: 2024/8/12
 * @Time: 下午6:08
 * @Description:
 */
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    LoginProtectedInterceptor loginProtectedInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectedInterceptor).addPathPatterns("/headline/**");
    }
}
