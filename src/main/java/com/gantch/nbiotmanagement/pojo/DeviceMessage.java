package com.gantch.nbiotmanagement.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-28-13:33
 * Description:  nbiot-management , com.gantch.nbiotmanagement.model
 **/
@Data
public class DeviceMessage {

    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "设备Id")
    private String deviceId;

    @ApiModelProperty(value = "前台用户Id")
    private Integer customerId;

    @ApiModelProperty(value = "推送手机号")
    private String phone;

    @ApiModelProperty(value = "推送通知启用状态 0-->未启用  1-—>启用普通报警 2-->启用APP通知报警 3-->启用周期报警(10分钟为一个周期)")
    private Integer status;

}
