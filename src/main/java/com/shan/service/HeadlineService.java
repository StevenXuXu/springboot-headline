package com.shan.service;

import com.shan.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shan.pojo.PortalVo;
import com.shan.utils.Result;

/**
* @author Steven
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-08-09 17:31:17
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer id);

    Result publish(Headline headline);

    Result findHeadlineByHid(String hid);
}
