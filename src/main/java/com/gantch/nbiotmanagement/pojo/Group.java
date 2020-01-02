package com.gantch.nbiotmanagement.pojo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author lcw332
 * Date 2019-12-24-14:45
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 **/
@Data
public class Group implements Serializable {

    @ApiModelProperty("分组ID")
    private String id;

    @ApiModelProperty("租户ID")
    private Integer tenantId;

    @ApiModelProperty("前台用户ID")
    private Integer customerId;

    @ApiModelProperty("分组名")
    private String name;



}
