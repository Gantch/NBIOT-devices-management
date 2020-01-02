package com.gantch.nbiotmanagement.service;

import com.gantch.nbiotmanagement.dto.DeviceCreateParam;
import com.gantch.nbiotmanagement.dto.DeviceUpdateParam;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-12:45
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service
 **/
public interface DeviceService {

    /**
     * 根据前台用户传入的MAC地址查找数据库并创建设备
     */
    Integer createDeviceByMac(DeviceCreateParam createParam);

    /**
     * 根据租户Id分页查询设备
     */
    List<DeviceRelation> list(Integer tenantId, Integer pageSize, Integer pageNum);

    /**
     * 根据设备Id修改设备昵称
     */
    Integer updateDeviceNickName(DeviceUpdateParam param);

    /**
     * 通过deviceId删除设备,设备组
     */
    @Transactional
    Integer deleteDevice(String deviceId);

    /**
     * 获取今日报警总数
     */
    Integer getTodayDeviceAlarmCount(Integer tenantId);

    /**
     * 获取昨日报警总数
     */
    Integer getYesTodayDeviceAlarmCount(Integer tenantId);

    /**
     * 获取租户总设备数
     */
    Integer getTenantDeviceCount(Integer tenantId);

    /**
     * 获取已分配的设备数量
     */
    Integer getAssignDeviceCount(Integer tenantId);

    /**
     * 获取未分配的设备数量
     */
    Integer getUnAssignDeviceCount(Integer tenantId);

    /**
     * 根据用户Id分页查询设备
     */
    List<DeviceRelation> getCustomerDevices(Integer customer,Integer pageSize,Integer pageNum);
}
