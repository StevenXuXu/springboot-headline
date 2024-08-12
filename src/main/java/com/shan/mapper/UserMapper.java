package com.shan.mapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.shan.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author Steven
* @description 针对表【news_user】的数据库操作Mapper
* @createDate 2024-08-09 17:31:17
* @Entity com.shan.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    List<User> selectByUsername(@Param("username") String username);
}