package com.gantch.nbiotmanagement.service;

import com.gantch.nbiotmanagement.dto.DeviceGroupCreateParam;
import com.gantch.nbiotmanagement.dto.DeviceGroupRelationParam;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.Group;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-15:09
 * Description:  nbiot-management , com.gantch.nbiotmanagement.service
 **/
public interface DeviceGroupService {

    /**
     * 创建设备组
     * @param group
     * @return
     */
    Integer createDeviceGroup(DeviceGroupCreateParam group);

    /**
     * 分配设备组与设备关系
     * @param deviceGroupRelationParam
     * @return
     */
    Integer createDeviceGroupRelation(DeviceGroupRelationParam deviceGroupRelationParam);

    /**
     * 通过GroupID删除设备组下所有设备
     */
    @Transactional
    Integer deleteDeviceGroup(String groupId);

    /**
     * 通过GroupId查找设备组下所有安装地址
     * @param groupId
     * @return
     */
    String findCustomerGroupDeviceSetUpAddressByGroupId(String groupId);

    /**
     * 通过GroupId获取设备总数
     */
    Integer findCustomerGroupDeviceCount(String groupId);

    /**
     * 根据租户Id分页查询设备组
     */
    List<Group> list(Integer tenantId, Integer pageSize, Integer pageNum);

    /**
     * 根据用户Id分页查询设备组
     */
    List<Group> getCustomerGroups(Integer customerId, Integer pageSize, Integer pageNum);

    /**
     * 根据用户Id分页查询设备组
     */
    List<DeviceRelation> findCustomerDeviceByGroupId(String groupId, Integer pageSize, Integer pageNum);


}
