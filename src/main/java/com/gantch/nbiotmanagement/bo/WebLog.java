package com.gantch.nbiotmanagement.bo;

import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-26-11:38
 * Description:  nbiot-management , com.gantch.nbiotmanagement.bo
 * Controller层的日志封装类
 **/
@Data
public class WebLog {
    /**
     * 操作描述
     */
    private String description;

    /**
     * 操作用户
     */
    private String username;

    /**
     * 操作时间
     */
    private Long startTime;

    /**
     * 消耗时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * IP地址
     */
    private String ip;

    private Object parameter;

    private Object result;
}
