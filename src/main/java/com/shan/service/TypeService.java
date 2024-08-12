package com.shan.service;

import com.shan.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shan.utils.Result;

/**
* @author Steven
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-08-09 17:31:17
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
