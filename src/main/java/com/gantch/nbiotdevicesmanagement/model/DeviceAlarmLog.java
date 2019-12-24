package com.gantch.nbiotdevicesmanagement.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lcw332
 * Date 2019-12-20-13:38
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 设备报警日志 nbiot_alarm_log
 **/
@Data
public class DeviceAlarmLog {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "设备ID")
    private String deviceId;

    @ApiModelProperty(value = "时间戳")
    private Date timeStamp;

    @ApiModelProperty(value = "设备名称")
    private String name;

    @ApiModelProperty(value = "经度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "维度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "报警内容")
    private String alarmType;


}
