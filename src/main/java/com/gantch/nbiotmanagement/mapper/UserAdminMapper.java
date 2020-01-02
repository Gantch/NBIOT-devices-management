package com.gantch.nbiotmanagement.mapper;


import com.gantch.nbiotmanagement.pojo.UserAdmin;
import com.gantch.nbiotmanagement.pojo.UserMember;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-18-11:41
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.mapper
 **/
@Mapper
public interface UserAdminMapper {

    @Select("SELECT * FROM user_admin WHERE username= #{username}")
    List<UserAdmin> selectByUsername(@Param("username") String username);

    @Insert("INSERT INTO user_admin (username, password, email,nick_name,note,create_time, login_time, status) " +
            "VALUES (#{username},#{password}, #{email},#{nickName},#{note},#{createTime}, #{loginTime}, #{status})")
    @Options(keyProperty = "id")
    Integer insert(UserAdmin record);

    @Update("UPDATE user_admin SET login_time = #{login_time} WHERE username = #{username}")
    Integer updateUserLoginTimeByUserName(@Param("loginTime") Date loginTime, @Param("username") String username);

    @Select("SELECT tenant_id from user_admin WHERE username = #{username}")
    Integer selectTenantIdByUsername(@Param("username") String username);

    @Update("UPDATE user_member SET tenant_id =#{tenantId} WHERE phone = #{phone}")
    Integer updateUserMemberTenantId(@Param("tenantId") Integer tenantId, @Param("phone") String phone);
//    @Select("SELECT * from user_admin WHERE username like '%{#username}%' OR nick_name like '%{nickName}%'")
    @Select("SELECT * from user_member WHERE tenant_id = #{tenantId}")
    List<UserMember> selectByTenantId(@Param("tenantId") Integer tenantId);

    @Delete("DELETE FROM user_member WHERE id = #{id}")
    Integer deleteByPrimaryKey(@Param("id") Integer id);


//    @Update("UPDATE ")
//    Integer updateUserAdminLoginLogByUsername(UserAdmin userAdmin, @Param("username")String username);
}
