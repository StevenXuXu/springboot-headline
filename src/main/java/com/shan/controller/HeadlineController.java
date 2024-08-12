package com.shan.controller;

import com.shan.mapper.HeadlineMapper;
import com.shan.pojo.Headline;
import com.shan.service.HeadlineService;
import com.shan.utils.JwtHelper;
import com.shan.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: Steven
 * @Date: 2024/8/12
 * @Time: 下午4:57
 * @Description:
 */
@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    HeadlineService headlineService;

    @Autowired
    HeadlineMapper headlineMapper;

    @Autowired
    JwtHelper jwtHelper;

    @PostMapping("publish")
    public Result publish(@RequestHeader String token, @RequestBody Headline headline) {
        Integer uid = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(uid);
        Result result = headlineService.publish(headline);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@RequestParam String hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        return result;
    }

    @PostMapping("update")
    public Result updateHeadline(@RequestBody Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();

        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineService.updateById(headline);
        return Result.ok(null);
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@RequestParam String hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
