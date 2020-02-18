package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.UserDeviceRelation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lcw332
 * Date 2020-01-11-8:55
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface UserRelationMapper {

    @Select("SELECT * FROM user_device_relation WHERE binded = #{binded}")
    List<UserDeviceRelation> selectUserDeviceRelationByBinded(@Param("binded")Integer binded);

    @Select("SELECT * FROM user_device_relation WHERE binded = #{binded} AND device_id = #{deviceId}")
    List<UserDeviceRelation> selectUserDeviceRelation();
}
