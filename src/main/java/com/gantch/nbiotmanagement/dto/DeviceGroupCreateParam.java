package com.gantch.nbiotmanagement.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lcw332
 * Date 2019-12-26-15:11
 * Description:  nbiot-management , com.gantch.nbiotmanagement.dto
 **/
@Data
public class DeviceGroupCreateParam {

    @ApiModelProperty("租户Id")
    private Integer tenantId;

    @ApiModelProperty("用户Id")
    private Integer customerId;

    @ApiModelProperty("分组名")
    private String name;
}
