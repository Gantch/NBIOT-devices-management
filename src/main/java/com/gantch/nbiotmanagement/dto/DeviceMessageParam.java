package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-28-14:06
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/

@Data
public class DeviceMessageParam {

    @ApiModelProperty(value = "前台用户手机号")
    private String phone;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "推送开启状态 0-->关闭 1-->开启")
    private Integer status;
}
