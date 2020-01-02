package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lcw332
 * Date 2019-12-26-12:49
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class DeviceCreateParam {

    @ApiModelProperty(value = "前台用户Id",required = true)
    private Integer customerId;

    @ApiModelProperty(value = "前台用户扫码，或手动输入",required = true)
    private String mac;

    @ApiModelProperty(value = "设备经度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "设备维度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "设备所在/区/县")
    private String district;

    @ApiModelProperty(value = "设备详细地址")
    private String location;

    @ApiModelProperty(value = "设备昵称")
    private String nickName;

    @ApiModelProperty(value = "用户租户ID")
    private Integer tenantId;
}
