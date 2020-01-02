package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.DeviceAlarmLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-27-10:20
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface DeviceAlarmMapper {

    @Select("SELECT * FROM nbiot_alarm_log WHERE TO_DAYS(timestamp) = TO_DAYS(NOW()) AND tenant_id = #{tenantId}")
    List<DeviceAlarmLog> selectAlarmTodayByTenantId(@Param("tenantId")Integer tenantId);

    @Select("SELECT * FROM nbiot_alarm_log WHERE TO_DAYS(NOW())-TO_DAYS(timestamp) = 1 AND tenant_id = #{tenantId}")
    List<DeviceAlarmLog> selectAlarmYesTodayByTenantId(Integer tenantId);
}
