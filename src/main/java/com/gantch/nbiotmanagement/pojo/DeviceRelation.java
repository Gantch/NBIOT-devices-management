package com.gantch.nbiotmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JacksonAnnotation;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author lcw332
 * Date 2019-12-20-13:26
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 设备详细 nbiot_deivce
 **/
@Data
public class DeviceRelation {

    @ApiModelProperty(value = "设备ID")
    private String id;

    @ApiModelProperty(value = "设备Mac地址")
    private String mac;

    @ApiModelProperty(value = "设备名")
    private String name;

    @ApiModelProperty(value = "设备昵称")
    private String nickName;

    @ApiModelProperty(value = "设备租户ID")
    private Integer tenantId;

    @ApiModelProperty(value = "绑定设备的用户Id")
    private Integer customerId;

    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @ApiModelProperty(value = "设备型号")
    private String model;

    @ApiModelProperty(value = "设备组ID")
    private String deviceGroupId;

    @ApiModelProperty(value = "设备经度")
    private BigDecimal latitude;

    @ApiModelProperty(value = "设备维度")
    private BigDecimal longitude;

    @ApiModelProperty(value = "设备所在/区/县")
    private String district;

    @ApiModelProperty(value = "设备详细地址")
    private String location;

    @JsonFormat(pattern = "yymm-MM-dd:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "设备创建时间")
    private Timestamp createTime;

    public DeviceRelation(String id, String mac, String name, String nickName, Integer tenantId, Integer customerId, String deviceType, String model, String deviceGroupId, BigDecimal latitude, BigDecimal longitude, String district, String location, Timestamp createTime) {
        this.id = id;
        this.mac = mac;
        this.name = name;
        this.nickName = nickName;
        this.tenantId = tenantId;
        this.customerId = customerId;
        this.deviceType = deviceType;
        this.model = model;
        this.deviceGroupId = deviceGroupId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.district = district;
        this.location = location;
        this.createTime = createTime;
    }
}