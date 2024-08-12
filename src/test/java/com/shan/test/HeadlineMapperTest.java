package com.shan.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shan.mapper.HeadlineMapper;
import com.shan.pojo.PortalVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @Author: Steven
 * @Date: 2024/8/12
 * @Time: 下午1:42
 * @Description:
 */
@SpringBootTest
public class HeadlineMapperTest {
    @Autowired
    private HeadlineMapper headlineMapper;

    @Test
    public void test() {
        PortalVo portalVo = new PortalVo(null, null, 1, 10);
        IPage<Map> page = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectNewsPage(page, portalVo);
        System.out.println(page.getRecords());
    }
}
