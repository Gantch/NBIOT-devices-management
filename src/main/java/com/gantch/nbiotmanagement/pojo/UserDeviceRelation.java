package com.gantch.nbiotmanagement.pojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-31-10:38
 * Description:  nbiot-management , com.gantch.nbiotmanagement.pojo
 **/
@Data
public class UserDeviceRelation {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "被分享者")
    private Integer binder;

    @ApiModelProperty(value = "设备组拥有者")
    private Integer binded;

    @ApiModelProperty(value = "设备组ID")
    private String groupId;

    @ApiModelProperty(value = "分享备注")
    private String remark;
}
