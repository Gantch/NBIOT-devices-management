package com.gantch.nbiotdevicesmanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lcw332
 * Date 2019-12-18-16:07
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 管理员登录日志 user_admin_login_log
 **/
@Data
public class UserAdminLoginLog implements Serializable {

    private Long id;

    private Long adminId;

    private Date createTime;

    private String ip;

    private String address;

    @ApiModelProperty(value = "浏览器登录类型")
    private String userAgent;

    private static final long serialVersionUID = 1L;
}
