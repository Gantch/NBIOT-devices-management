package com.gantch.nbiotmanagement.service.impl;

import com.gantch.nbiotmanagement.dto.DeviceGroupCreateParam;
import com.gantch.nbiotmanagement.dto.DeviceGroupRelationParam;
import com.gantch.nbiotmanagement.mapper.DeviceGroupMapper;
import com.gantch.nbiotmanagement.mapper.DeviceMapper;
import com.gantch.nbiotmanagement.mapper.UserMemberMapper;
import com.gantch.nbiotmanagement.pojo.DeviceGroupRelation;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.Group;
import com.gantch.nbiotmanagement.service.DeviceGroupService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;

/**
 * @author lcw332
 * Date 2019-12-26-15:09
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service.impl
 **/
@Service
public class DeviceGroupServiceImpl implements DeviceGroupService {

    @Autowired
    private DeviceGroupMapper deviceGroupMapper;

    @Autowired
    private DeviceMapper deviceMapper;

    @Autowired
    private UserMemberMapper memberMapper;

    @Override
    public Integer createDeviceGroup(DeviceGroupCreateParam group) {
        if(memberMapper.selectUserMemberById(group.getCustomerId())>0){
            Group deviceGroup=new Group();
            deviceGroup.setId(UUID.randomUUID().toString());
            deviceGroup.setCustomerId(group.getCustomerId());
            deviceGroup.setTenantId(group.getTenantId());
            deviceGroup.setName(group.getName());
            return deviceGroupMapper.insertDeviceGroup(deviceGroup);
        }
        return null;
    }

    @Override
    public Integer createDeviceGroupRelation(DeviceGroupRelationParam param) {
        String groupId = param.getGroupId();
        String deviceId = param.getDeviceId();
        if (deviceMapper.selectDeviceRelation(deviceId)==null) {
            System.out.println("没有找到该设备的Id");
            return null;
        }
        List<DeviceGroupRelation> relation=deviceGroupMapper.selectDeviceGroupRelation(groupId,deviceId);
        if (relation.size()>0){
            return null;
        }
        Integer updateDeviceRelationResult=deviceMapper.updateDeviceRelationGroupIdByDeviceID(param.getGroupId(),param.getDeviceId());
        deviceGroupMapper.insertDeviceGroupRelation(param.getGroupId(),param.getDeviceId());
        System.out.println(param);
        if (updateDeviceRelationResult!=null){
            return updateDeviceRelationResult;
        }
        return null;
    }

    @Override
    public Integer deleteDeviceGroup(String groupId) {
        return deviceGroupMapper.deleteDeviceGroup(groupId);
    }

    @Override
    public List<Group> list(Integer tenantId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if (!StringUtils.isEmpty(tenantId)){
            return deviceGroupMapper.selectDeviceGroupByTenant(tenantId);
        }
        return null;
    }

    @Override
    public List<Group> getCustomerGroups(Integer customerId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if(!StringUtils.isEmpty(customerId)){
            return deviceGroupMapper.selectDeviceGroupByCustomerId(customerId);
        }
        return null;
    }

    @Override
    public List<DeviceRelation> findCustomerDeviceByGroupId(String groupId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if(!StringUtils.isEmpty(groupId)){
            return deviceGroupMapper.selectDeviceByGroupId(groupId);
        }
        return null;
    }


}
