package com.gantch.nbiotdevicesmanagement.model;

import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.gantch.nbiotdevicesmanagement.dao.BaseEntity;
import com.gantch.nbiotdevicesmanagement.dao.SearchTextBased;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

import static com.gantch.nbiotdevicesmanagement.dao.ModelConstants.*;
/**
 * @author lcw332
 * Date 2019-12-24-14:07
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.model
 * 设备Token关系表
 **/
@Table(name = DEVICE_CREDENTIALS_COLUMN_FAMILY_NAME)
public class DeviceCredentials extends SearchTextBased implements BaseEntity {

    @PartitionKey(value = 0)
    @Column(name = ID_PROPERTY)
    @ApiModelProperty(value = "id")
    private UUID id;

    @ApiModelProperty(value = "设备Id")
    @Column(name = DEVICE_CREDENTIALS_DEVICE_ID_PROPERTY)
    private UUID deviceId;

    @ApiModelProperty(value = "设备Token")
    @Column(name = DEVICE_CREDENTIALS_TOKEN_PROPERTY )
    private String deviceToken;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(UUID deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @Override
    public String getSearchText() {
        return "";
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append("\""+id+"\"");
        sb.append(",\"deviceId\":")
                .append(deviceId);
        sb.append(",\"deviceToken\":\"")
                .append(deviceToken).append('\"');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceCredentials that = (DeviceCredentials) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (deviceId != null ? !deviceId.equals(that.deviceId) : that.deviceId != null) return false;
        return deviceToken != null ? deviceToken.equals(that.deviceToken) : that.deviceToken == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (deviceId != null ? deviceId.hashCode() : 0);
        result = 31 * result + (deviceToken != null ? deviceToken.hashCode() : 0);
        return result;
    }
}
