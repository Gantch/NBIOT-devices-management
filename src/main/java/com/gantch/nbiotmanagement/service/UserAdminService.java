package com.gantch.nbiotmanagement.service;

import com.gantch.nbiotmanagement.dto.UserAdminParam;
import com.gantch.nbiotmanagement.pojo.UserAdmin;
import com.gantch.nbiotmanagement.pojo.UserMember;
import com.gantch.nbiotmanagement.pojo.UserPermission;
import com.gantch.nbiotmanagement.pojo.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-18-11:37
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service
 * 后台管理员Service
 **/
public interface UserAdminService {


    /**
     * 获取用户对于角色
     */
    List<UserRole> getRoleList(Long adminId);

    /**
     * 根据ID删除前台指定用户
     */
    Integer delete(Integer id);

    /**
     * 根据手机号查找并修改前台用户租户Id
     */
    Integer updateUserMemberTenantId(String username, String phone);

    /**
     * 注册功能
     */
    UserAdmin register(UserAdminParam umsAdminParam);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<UserMember> list(String username, Integer pageSize, Integer pageNum);
    /**
     * 根据用户名获取后台管理员
     */
    UserAdmin getAdminByUsername(String username);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 获取用户信息
     */
    UserDetails loadUserByUsername(String username);


    /**
     * 获取用户所有权限（包括角色权限和+-权限）
     */
    List<UserPermission> getPermissionList(Long adminId);


}
