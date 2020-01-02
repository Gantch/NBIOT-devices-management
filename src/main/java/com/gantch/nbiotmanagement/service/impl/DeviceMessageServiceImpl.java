package com.gantch.nbiotmanagement.service.impl;

import com.gantch.nbiotmanagement.dto.DeviceMessageParam;
import com.gantch.nbiotmanagement.mapper.DeviceMessageMapper;
import com.gantch.nbiotmanagement.mapper.UserMemberMapper;
import com.gantch.nbiotmanagement.pojo.DeviceMessage;
import com.gantch.nbiotmanagement.pojo.UserMember;
import com.gantch.nbiotmanagement.service.DeviceMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-28-13:40
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service.impl
 **/

@Service
public class DeviceMessageServiceImpl implements DeviceMessageService {

    @Autowired
    private DeviceMessageMapper deviceMessageMapper;

    @Autowired
    private UserMemberMapper userMemberMapper;


    @Override
    public Integer insertDeviceMessage(DeviceMessageParam param) {
        DeviceMessage deviceMessage=new DeviceMessage();
        deviceMessage.setDeviceId(param.getDeviceId());
        List<DeviceMessage> deviceMessage2 =deviceMessageMapper.selectDeviceMessageByPhoneDeviceId(param.getPhone(),param.getDeviceId());
        if (deviceMessage2.size()>0) {//表示表nbiot_device中已有此手机号,请勿重新添加
            return null;
        }
        if(deviceMessage2.size() ==0) {//表示改设备未创建过手机号
            List<UserMember> memberList = userMemberMapper.selectUserMemberByPhone(param.getPhone());
            if (memberList.size() < 1) {//列表小于1 表示前台用户中未找到此手机
                System.out.println("未找到该手机号");
                return null;
            }
            Integer customerId = userMemberMapper.selectUserCustomerIdByPhone(param.getPhone());//拿到CustomerId
            deviceMessage.setCustomerId(customerId);
            deviceMessage.setPhone(param.getPhone());
            deviceMessage.setStatus(param.getStatus());
            if (deviceMessage.getDeviceId() == null) {
                return null;
            }
        }
        return deviceMessageMapper.insertDeviceMessage(deviceMessage);
    }

    @Override
    public Integer updateDevicePushStatus(String deviceId, Integer status) {
        System.out.println(deviceId);
        if (deviceMessageMapper.selectDeviceMessageByDeviceId(deviceId)==null){
            return null;
        }
        return deviceMessageMapper.updateDeviceMessageStatusByDeviceId(status,deviceId);
    }

}
