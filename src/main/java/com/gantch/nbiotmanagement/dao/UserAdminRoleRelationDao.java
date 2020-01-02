package com.gantch.nbiotmanagement.dao;



import com.gantch.nbiotmanagement.pojo.UserAdminRoleRelation;
import com.gantch.nbiotmanagement.pojo.UserPermission;
import com.gantch.nbiotmanagement.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-18-15:06
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.dao
 * 后台用户与角色管理系统自定义Dao
 **/
@Mapper
public interface UserAdminRoleRelationDao {

    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("List") List<UserAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用户所有角色
     */
    @Select("SELECT r.* FROM user_admin_role_relation ar " +
            "LEFT JOIN user_role r ON ar.role_id = r.id " +
            "WHERE ar.admin_id = #{adminId}")
    List<UserRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限
     */
    @Select("SELECT p.* FROM user_admin_role_relation ar " +
            "LEFT JOIN user_role r ON ar.role_id = r.id " +
            "LEFT JOIN user_role_permission_relation rp ON r.id = rp.role_id " +
            "LEFT JOIN user_permission p ON rp.permission_id = p.id " +
            "WHERE " +
            "ar.admin_id =#{adminId} " +
            "AND p.id IS NOT NULL " +
            "AND p.id NOT IN(" +
            "SELECT p.id " +
            "FROM " +
            "user_admin_permission_relation pr " +
            "LEFT JOIN user_permission p ON pr.permission_id = p.id " +
            "WHERE " +
            "pr.type = -1 " +
            "AND pr.admin_id = #{adminId})" +
            "UNION " +
            "SELECT p.* " +
            "FROM user_admin_permission_relation pr " +
            "LEFT JOIN user_permission p ON pr.permission_id = p.id " +
            "WHERE " +
            "pr.type = 1 " +
            "AND pr.admin_id =#{adminId}")
    List<UserPermission> getPermissionList(@Param("adminId") Long adminId);

}
