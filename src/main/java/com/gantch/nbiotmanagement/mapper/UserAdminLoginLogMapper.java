package com.gantch.nbiotmanagement.mapper;

import com.gantch.nbiotmanagement.pojo.UserAdminLoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author lcw332
 * Date 2019-12-18-16:13
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.mapper
 **/
@Mapper
public interface UserAdminLoginLogMapper {

    @Insert("INSERT into user_admin_login_log(admin_id,create_time,ip,address,user_agent) VALUES (#{adminId},#{createTime},#{ip},#{address},#{userAgent})")
    @Options(keyProperty = "id")
    Integer insert(UserAdminLoginLog record);
}
