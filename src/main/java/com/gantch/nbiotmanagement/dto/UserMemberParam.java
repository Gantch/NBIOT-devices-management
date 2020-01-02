package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-31-10:10
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class UserMemberParam {

    @ApiModelProperty(value = "前台用户名,设备组拥有者ID")
    private Integer customerId;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(value = "设备组ID数组")
    private String groupId;


}
