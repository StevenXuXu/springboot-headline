package com.shan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: Steven
 * @Date: 2024/8/9
 * @Time: 下午10:48
 * @Description:
 */
@Data
@AllArgsConstructor
public class PortalVo {

    private String keyWords;

    private Integer type;

    private Integer pageNum;

    private Integer pageSize;
}
