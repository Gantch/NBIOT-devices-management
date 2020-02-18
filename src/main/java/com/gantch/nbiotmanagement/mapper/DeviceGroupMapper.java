package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.DeviceGroupRelation;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import com.gantch.nbiotmanagement.pojo.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-15:15
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface DeviceGroupMapper {

    @Insert("INSERT INTO nbiot_group(id,tenant_id,customer_id,name) VALUES (#{id},#{tenantId},#{customerId},#{name})")
    @Options(keyProperty = "id")
    Integer insertDeviceGroup(Group group);

    @Insert("INSERT INTO nbiot_group_relation(group_id,device_id) VALUES (#{groupId},#{deviceId})")
    Integer insertDeviceGroupRelation(@Param("groupId")String groupId,@Param("deviceId")String deviceId);

//    @Delete("DELETE nbiot_device_relation,nbiot_group_relation FROM nbiot_device_relation " +
//            "LEFT JOIN nbiot_group_relation ON nbiot_group_relation.device_id = nbiot_device_relation.id " +
//            "WHERE nbiot_device_relation.id = #{deviceId}")

    @Delete("DELETE nbiot_group,nbiot_group_relation,nbiot_device_relation FROM nbiot_group " +
            "LEFT JOIN nbiot_group_relation ON nbiot_group_relation.group_id = nbiot_group.id " +
            "LEFT JOIN nbiot_device_relation ON nbiot_device_relation.device_group_id = nbiot_group.id " +
            "WHERE nbiot_group.id = #{groupId}")
    Integer deleteDeviceGroup(@Param("groupId")String groupId);

    @Select("SELECT * FROM nbiot_group WHERE tenant_id = #{tenantId}")
    List<Group> selectDeviceGroupByTenant(@Param("tenantId")Integer tenantId);

    @Select("SELECT * FROM nbiot_group_relation WHERE group_id=#{groupId} AND device_id=#{deviceId}")
    List<DeviceGroupRelation> selectDeviceGroupRelation(@Param("groupId")String groupId, @Param("deviceId")String deviceId);

    @Select("SELECT * FROM nbiot_group WHERE customer_id = #{customerId}")
    List<Group> selectDeviceGroupByCustomerId(@Param("customerId")Integer customerId);

    @Select("SELECT * FROM nbiot_device_relation WHERE device_group_id = #{groupId}")
    List<DeviceRelation> selectDeviceByGroupId(@Param("groupId")String groupId);
}
