package com.gantch.nbiotmanagement.service.impl;

import cn.hutool.core.util.StrUtil;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    public String findCustomerGroupDeviceSetUpAddressByGroupId(String groupId) {
        List<String> result=deviceGroupMapper.selectGroupDeviceSetUpAddress(groupId);
        //list去重，拿到新的list
        List<String> newList=result.stream().distinct().collect(Collectors.toList());
        String str = StringUtils.join(newList,",");
        return str;
    }

    @Override
    public Integer findCustomerGroupDeviceCount(String groupId) {
        return deviceGroupMapper.selectGroupDeviceCountByGroupId(groupId);
    }

    @Override
    public List<Group> list(Integer tenantId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if (!StrUtil.isEmpty(tenantId.toString())){
            return deviceGroupMapper.selectDeviceGroupByTenant(tenantId);
        }
        return null;
    }

    @Override
    public List<Group> getCustomerGroups(Integer customerId, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        if(!StrUtil.isEmpty(customerId.toString())){
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
