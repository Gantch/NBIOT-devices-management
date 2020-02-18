package com.gantch.nbiotmanagement.service.impl;

import com.gantch.nbiotmanagement.dto.DeviceCreateParam;
import com.gantch.nbiotmanagement.dto.DeviceUpdateParam;
import com.gantch.nbiotmanagement.mapper.DeviceAlarmMapper;
import com.gantch.nbiotmanagement.mapper.DeviceMapper;
import com.gantch.nbiotmanagement.mapper.UserMemberMapper;
import com.gantch.nbiotmanagement.pojo.Device;
import com.gantch.nbiotmanagement.pojo.DeviceAlarmLog;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.service.DeviceService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-12:46
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service.impl
 **/
@Service
public class DeviceServiceImpl implements DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceImpl.class);

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private DeviceAlarmMapper alarmMapper;

    @Autowired
    private UserMemberMapper memberMapper;

    @Override
    public Integer createDeviceByMac(DeviceCreateParam createParam) {
            if(memberMapper.selectUserMemberById(createParam.getCustomerId())<0) {
                System.out.println(memberMapper.selectUserMemberById(createParam.getCustomerId()));
                return null;
            }//验证用户ID是否存在
            Device deviceByMac = deviceMapper.selectDeviceByMac(createParam.getMac());//验证MAC地址是否在nbiot_device存在
            System.out.println(deviceByMac);
            if (deviceByMac.getMac() == null) {
                return null;
            }
            String groupId=null;
            String deviceId=deviceByMac.getDeviceId();
            DeviceRelation relation = new DeviceRelation(deviceId,deviceByMac.getMac(),deviceByMac.getName(),
                    createParam.getNickName(),createParam.getTenantId(),createParam.getCustomerId(),deviceByMac.getDeviceType(),
                    deviceByMac.getModel(),groupId,createParam.getLatitude(),createParam.getLongitude(),createParam.getDistrict(),
                    createParam.getLocation(),new Timestamp(System.currentTimeMillis()));
            System.out.println(relation);
            String selectRelation = deviceMapper.selectDeviceRelation(deviceByMac.getDeviceId());//验证是否在nbiot_device_relation中有绑定关系
            if (selectRelation ==null){//如果没有绑定关系
                Integer result=deviceMapper.insertDeviceRelationByCustomerId(relation);
                if (result>0) {
                    return result;
                }
            }

        return null;
    }

    @Override
    public List<DeviceRelation> list(Integer tenantId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if (!StringUtils.isEmpty(tenantId)){
            return deviceMapper.selectDeviceRelationByTenant(tenantId);
        }
        return null;
    }

    @Override
    public Integer updateDeviceNickName(DeviceUpdateParam param) {
        String deviceId = param.getDeviceId();
        String nickName = param.getNickName();
        String relation = deviceMapper.selectDeviceRelation(deviceId);
        if (relation == null){
            return null;
        }
        return deviceMapper.updateDeviceNickNameByDeviceId(nickName,deviceId);
    }

    @Override
    public Integer deleteDevice(String deviceId) {
        deviceMapper.deleteDeviceGroupRelationByDeviceId(deviceId);
        return deviceMapper.deleteDeviceByDeviceId(deviceId);
    }

    @Override
    public Integer getTodayDeviceAlarmCount(Integer tenantId) {
        List<DeviceAlarmLog> result =alarmMapper.selectAlarmTodayByTenantId(tenantId);
        return result.size();
    }

    @Override
    public Integer getYesTodayDeviceAlarmCount(Integer tenantId) {
        List<DeviceAlarmLog> result =alarmMapper.selectAlarmYesTodayByTenantId(tenantId);
        return result.size();
    }

    @Override
    public Integer getTenantDeviceCount(Integer tenantId) {
        List<DeviceRelation> result = deviceMapper.selectDeviceRelationByTenant(tenantId);
        return result.size();
    }

    @Override
    public Integer getAssignDeviceCount(Integer tenantId) {
        List<DeviceRelation> result = deviceMapper.selectAssignDeviceCountByTenant(tenantId);
        return result.size();
    }

    @Override
    public Integer getUnAssignDeviceCount(Integer tenantId) {
        List<DeviceRelation> result = deviceMapper.selectUnAssignDeviceCountByTenant(tenantId);
        return result.size();
    }

    @Override
    public List<DeviceRelation> getCustomerDevices(Integer customer, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if (!StringUtils.isEmpty(customer)){
            return deviceMapper.selectDeviceRelationByCustomerId(customer);
        }
        return null;
    }

}
