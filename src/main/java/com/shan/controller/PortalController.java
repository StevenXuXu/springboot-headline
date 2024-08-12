package com.shan.controller;

import com.shan.pojo.PortalVo;
import com.shan.service.HeadlineService;
import com.shan.service.TypeService;
import com.shan.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Steven
 * @Date: 2024/8/9
 * @Time: 下午10:09
 * @Description:
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    private static final Logger log = LoggerFactory.getLogger(PortalController.class);
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        Result result = headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
