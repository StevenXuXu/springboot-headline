package com.shan.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shan.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shan.pojo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author Steven
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-08-09 17:31:17
* @Entity com.shan.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectNewsPage(IPage<Map> page, @Param("portalVo") PortalVo portalVo);

    Map showHeadlineDetail(Integer hid);
}




