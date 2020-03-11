package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.Device;
import com.gantch.nbiotmanagement.pojo.DeviceRelation;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-13:09
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface DeviceMapper {

    @Select("SELECT * FROM nbiot_device WHERE mac = #{mac}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Device selectDeviceByMac(@Param("mac")String mac);

    @Select("SELECT * FROM nbiot_device_relation WHERE tenant_id = #{tenantId}")
    List<DeviceRelation> selectDeviceRelationByTenant(@Param("tenantId")Integer tenantId);

    @Select("SELECT * FROM nbiot_device_relation WHERE customer_id = #{customerId}")
    List<DeviceRelation> selectDeviceRelationByCustomerId(@Param("customerId")Integer customerId);

    @Select("SELECT * FROM nbiot_device_relation WHERE tenant_id = #{tenantId} AND device_group_id !=''")
    List<DeviceRelation> selectAssignDeviceCountByTenant(@Param("tenantId")Integer tenantId);

    @Select("SELECT * FROM nbiot_device_relation WHERE tenant_id = #{tenantId} AND device_group_id =''")
    List<DeviceRelation> selectUnAssignDeviceCountByTenant(@Param("tenantId")Integer tenantId);

    @Select("SELECT id FROM nbiot_device_relation WHERE id =#{deviceId}")
    String selectDeviceRelation(@Param("deviceId")String deviceId);

    @Update("UPDATE nbiot_device_relation SET device_group_id = #{groupId} WHERE id = #{id}")
    Integer updateDeviceRelationGroupIdByDeviceID(@Param("groupId")String groupId,@Param("id")String deviceId);

    @Update("UPDATE nbiot_device_relation " +
            "SET latitude = #{latitude} " +
            ", longitude = #{longitude} " +
            ", district = #{district} " +
            ", location = #{location} " +
            "WHERE id = #{id}")
    Integer updateDeviceInfoByDeviceId(@Param("id")String id,@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("district") String district, @Param("location") String location);

    @Update("UPDATE nbiot_device_relation SET nick_name = #{nickName} WHERE id = #{id}")
    Integer updateDeviceNickNameByDeviceId(@Param("nickName")String nickName,@Param("id")String deviceId);

    @Insert("INSERT INTO nbiot_device_relation(id,mac,name,nick_name,tenant_id,customer_id,device_type,model,device_group_id,latitude,longitude,district,location,create_time) VALUES (#{id},#{mac},#{name},#{nickName},#{tenantId},#{customerId},#{deviceType},#{model},#{deviceGroupId},#{latitude},#{longitude},#{district},#{location},#{createTime})")
//    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    Integer insertDeviceRelationByCustomerId(DeviceRelation deviceRelation);

    @Select("SELECT id FROM nbiot_device_relation WHERE mac = #{mac}")
    String selectDeviceRelationByMac(@Param("mac")String mac);

    @Delete("DELETE FROM nbiot_device_relation WHERE id = #{deviceId}")
    Integer deleteDeviceRelationByDeviceId(@Param("deviceId")String deviceId);

    @Delete("DELETE FROM nbiot_group_relation WHERE device_id = #{deviceId}")
    Integer deleteDeviceGroupRelationByDeviceId(@Param("deviceId")String deviceId);

    @Delete("DELETE nbiot_device_relation,nbiot_group_relation FROM nbiot_device_relation " +
            "LEFT JOIN nbiot_group_relation ON nbiot_group_relation.device_id = nbiot_device_relation.id " +
            "WHERE nbiot_device_relation.id = #{deviceId}")
    Integer deleteDeviceByDeviceId(@Param("deviceId")String deviceId);


}
