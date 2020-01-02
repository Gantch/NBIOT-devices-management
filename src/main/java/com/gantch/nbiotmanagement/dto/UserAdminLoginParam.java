package com.gantch.nbiotmanagement.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author lcw332
 * Date 2019-12-19-11:28
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dto
 * 用户登录参数
 **/
@Data
public class UserAdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @ApiModelProperty(value ="密码",required = true)
    @NotEmpty(message = "密码不能为空")
    private String password;

}
