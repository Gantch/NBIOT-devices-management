package com.gantch.nbiotmanagement.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


/**
 * @author lcw332
 * Date 2019-12-20-13:26
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 设备详细 nbiot_deivce
 **/
@Data
public class Device {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "设备Mac地址")
    private String mac;

    @ApiModelProperty(value = "设备名")
    private String name;

    @ApiModelProperty(value = "设备id")
    private String deviceId;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "设备型号")
    private String model;

    @ApiModelProperty(value = "设备创建时间")
    private Date createTime;
}