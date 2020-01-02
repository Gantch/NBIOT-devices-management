package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-28-14:49
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class DeviceMessageUpdateParam {

    @ApiModelProperty(value = "设备Id")
    private String deviceId;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
