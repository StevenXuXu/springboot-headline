package com.shan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shan.pojo.Type;
import com.shan.service.TypeService;
import com.shan.mapper.TypeMapper;
import com.shan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Steven
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-08-09 17:31:17
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}




