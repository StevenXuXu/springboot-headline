package com.shan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shan.pojo.Headline;
import com.shan.pojo.PortalVo;
import com.shan.service.HeadlineService;
import com.shan.mapper.HeadlineMapper;
import com.shan.utils.JwtHelper;
import com.shan.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Steven
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-08-09 17:31:17
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectNewsPage(page, portalVo);
        Map data = new HashMap();
        data.put("pageData", page.getRecords());
        data.put("pageNum", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize", page.getTotal());
        Map result = new HashMap();
        result.put("pageInfo", data);
        return Result.ok(result);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map map = headlineMapper.showHeadlineDetail(hid);
        Map data = new HashMap();
        data.put("headline", map);

        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) map.get("pageViews") + 1);
        headline.setVersion((Integer) map.get("version"));
        headlineMapper.updateById(headline);

        return Result.ok(data);
    }

    @Override
    public Result publish(Headline headline) {
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headline.setPageViews(0);
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(String hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map data = new HashMap();
        data.put("headline", headline);
        return Result.ok(data);
    }
}




