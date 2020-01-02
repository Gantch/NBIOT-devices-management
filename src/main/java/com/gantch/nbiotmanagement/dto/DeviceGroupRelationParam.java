package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-26-15:46
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class DeviceGroupRelationParam {

    @ApiModelProperty(value = "设备组ID")
    private String groupId;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;
}
