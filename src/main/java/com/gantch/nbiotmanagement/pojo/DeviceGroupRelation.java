package com.gantch.nbiotmanagement.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-26-15:47
 * Description:  nbiot-management , com.gantch.nbiotmanagement.model
 **/

@Data
public class DeviceGroupRelation {

    @ApiModelProperty(value = "设备组ID")
    private String groupId;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;
}

