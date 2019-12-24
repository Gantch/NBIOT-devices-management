package com.gantch.nbiotdevicesmanagement.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.gantch.nbiotdevicesmanagement.dao.SearchTextBased;
import com.gantch.nbiotdevicesmanagement.dao.SearchTextEntity;
import com.google.gson.annotations.Expose;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import static com.gantch.nbiotdevicesmanagement.dao.ModelConstants.*;

/**
 * @author lcw332
 * Date 2019-12-20-13:26
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 设备详细 nbiot_deivce
 **/
@Table(name = NBIOT_DEVICE_COLUMN_FAMILY_NAME)
public class Device extends SearchTextBased implements SearchTextEntity, Serializable {

    private static final long serialVersionUID = 1L;

    @PartitionKey(value = 0)
    @Column(name = ID_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备ID")
    private UUID id;

    @Column(name = DEVICE_MAC_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备Mac地址")
    private String mac;

    @Column(name = DEVICE_NAME_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备名")
    private String name;

    @Column(name = DEVICE_NICKNAME_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备昵称")
    private String nickName;

    @Column(name = DEVICE_TENANT_ID_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备租户ID")
    private Integer tenantId;

    @Column(name = DEVICE_CUSTOMER_ID_PROPERTY)
    @Expose
    @ApiModelProperty(value = "绑定设备的用户Id")
    private Integer customerId;

    @Column(name = DEVICE_DEVICE_TYPE_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备类型")
    private String deviceType;

    @Column(name = DEVICE_MODEL_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备型号")
    private String model;

    @Column(name = DEVICE_GROUP_ID_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备组ID")
    private String deviceGroupId;

    @Column(name = DEVICE_LATITUDE_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备经度")
    private BigDecimal latitude;

    @Column(name = DEVICE_LONGITUDE_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备维度")
    private BigDecimal longitude;

    @Column(name = DEVICE_DISTRICT_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备所在/区/县")
    private String district;

    @Column(name = DEVICE_LOCATION_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备详细地址")
    private String location;

    @Column(name = SEARCH_TEXT_PROPERTY)
    @Expose
    @ApiModelProperty(value = "搜索关键字")
    private String searchText;

    @Column(name = DEVICE_CREATE_TIME_PROPERTY)
    @Expose
    @ApiModelProperty(value = "设备创建时间")
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDeviceGroupId() {
        return deviceGroupId;
    }

    public void setDeviceGroupId(String deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String getSearchText() {
        return searchText;
    }

    @Override
    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append("\"" + id + "\"");
        sb.append(",\"tenantId\":")
                .append(tenantId);
        sb.append(",\"customerId\":")
                .append(customerId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"searchText\":\"")
                .append(searchText).append('\"');
        sb.append(",\"deviceGroupId\":\"")
                .append(deviceGroupId).append('\"');
        sb.append(",\"deviceType\":\"")
                .append(deviceType).append('\"');
        sb.append(",\"model\":\"")
                .append(model).append('\"');
        sb.append(",\"location\":\"")
                .append(location).append('\"');
        sb.append(",\"nickname\":\"")
                .append(nickName).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public long getCreatedTime() {
        Long createdTime = id.timestamp();
        createdTime = createdTime/10000000L - 12219292800L;
        return createdTime;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (tenantId != null ? tenantId.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (searchText != null ? searchText.hashCode() : 0);
        result = 31 * result + (deviceGroupId != null ? deviceGroupId.hashCode() : 0);
        result = 31 * result + (deviceType != null ? deviceType.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        return result;
    }

    @Override
    public String getSearchTextSource() {
        return getName();
    }
}
