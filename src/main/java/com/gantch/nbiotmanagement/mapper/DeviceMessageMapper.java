package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.DeviceMessage;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-28-13:34
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface DeviceMessageMapper {

    @Select("SELECT * FROM nbiot_device_message WHERE phone = #{phone} AND device_id = #{deviceId}")
    List<DeviceMessage> selectDeviceMessageByPhoneDeviceId(@Param("phone")String phone, @Param("deviceId")String deviceId);

    @Select("SELECT * FROM nbiot_device_message WHERE device_id =#{deviceId}")
    Integer selectDeviceMessageByDeviceId(@Param("deviceId") String deviceId);

    @Insert("INSERT INTO nbiot_device_message(device_id,customer_id,phone,status) VALUES (#{deviceId},#{customerId},#{phone},#{status})")
    @Options(keyProperty = "id",keyColumn = "id",useGeneratedKeys = true)
    Integer insertDeviceMessage(DeviceMessage deviceMessage);

    @Update("UPDATE nbiot_device_message SET status =#{status} WHERE device_id = #{deviceId}")
    Integer updateDeviceMessageStatusByDeviceId(@Param("status")Integer status,@Param("deviceId")String deviceId);
}
