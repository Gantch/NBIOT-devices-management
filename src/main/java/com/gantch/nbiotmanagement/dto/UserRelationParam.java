package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2020-01-11-9:04
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class UserRelationParam {

    @ApiModelProperty(value = "设备拥有者")
    private String customerId;

    @ApiModelProperty(value = "被分享者的平台手机号")
    private String phone;

    @ApiModelProperty(value = "被分享的设备Id")
    private String deviceId;

    @ApiModelProperty(value = "分享备注")
    private String remark;
}
