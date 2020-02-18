package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.dto.UserMemberParam;
import com.gantch.nbiotmanagement.pojo.UserDeviceRelation;
import com.gantch.nbiotmanagement.pojo.UserMember;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-26-13:40
 * Description:  nbiot-management , com.gantch.nbiotmanagement.mapper
 **/
@Mapper
public interface UserMemberMapper {

    @Select("SELECT * FROM user_member WHERE id =#{id}")
    Integer selectUserMemberById(@Param("id")Integer id);

    @Select("SELECT * FROM user_member WHERE phone =#{phone}")
    List<UserMember> selectUserMemberByPhone(@Param("phone")String phone);

    @Select("SELECT id FROM user_member WHERE phone = #{phone}")
    Integer selectUserCustomerIdByPhone(@Param("phone")String phone);

}
