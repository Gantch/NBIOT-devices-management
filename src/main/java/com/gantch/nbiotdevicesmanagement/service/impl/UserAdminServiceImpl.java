package com.gantch.nbiotdevicesmanagement.service.impl;

import com.gantch.nbiotdevicesmanagement.bo.AdminUserDetails;
import com.gantch.nbiotdevicesmanagement.dao.useradmin.UserAdminRoleRelationDao;
import com.gantch.nbiotdevicesmanagement.dto.UserAdminParam;
import com.gantch.nbiotdevicesmanagement.mapper.UserAdminLoginLogMapper;
import com.gantch.nbiotdevicesmanagement.mapper.UserAdminMapper;
import com.gantch.nbiotdevicesmanagement.model.*;
import com.gantch.nbiotdevicesmanagement.service.UserAdminService;
import com.gantch.nbiotdevicesmanagement.utils.JwtTokenUtil;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-18-11:41
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.service.impl
 * UerAdminService实现类
 **/
@Service
public class UserAdminServiceImpl implements UserAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAdminServiceImpl.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;//密码加密

    @Autowired
    private UserAdminRoleRelationDao adminRoleRelationDao;

    @Autowired
    private UserAdminMapper userAdminMapper;

    @Autowired
    private UserAdminLoginLogMapper loginLogMapper;


    @Override
    public List<UserRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public Integer delete(Integer id) {
        return userAdminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer updateUserMemberTenantId(String username,String phone) {
        Integer tenantId=userAdminMapper.selectTenantIdByUsername((username));
        return userAdminMapper.updateUserMemberTenantId(tenantId,phone);
    }

    @Override
    public UserAdmin register(UserAdminParam userAdminParam) {
        UserAdmin userAdmin = new UserAdmin();
        BeanUtils.copyProperties(userAdminParam,userAdmin);
        userAdmin.setCreateTime(new Date());
        userAdmin.setStatus(1);
        //查询是否有相同的userName
//        userAdminMapper.selectByUsername(userAdmin.getUsername());
        userAdmin.setUsername(userAdminParam.getUsername());
        List<UserAdmin> userAdminList=userAdminMapper.selectByUsername(userAdmin.getUsername());
        if (userAdminList.size()>0){
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(userAdmin.getPassword());
        userAdmin.setPassword(encodePassword);
        userAdminMapper.insert(userAdmin);
        return userAdmin;
    }

    @Override
    public List<UserMember> list(String username, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        UserAdmin userAdmin = new UserAdmin();
        if (!StringUtils.isEmpty(username)){
            userAdmin.setUsername(username);
            //返回租户Id下前台用户
            return userAdminMapper.selectByTenantId(userAdminMapper.selectTenantIdByUsername(username));
        }
        return null;
    }

    //获取所有管理员列表
    @Override
    public UserAdmin getAdminByUsername(String username) {
        UserAdmin userAdmin = new UserAdmin();
        userAdmin.setUsername(username);
        List<UserAdmin> adminList = userAdminMapper.selectByUsername(userAdmin.getUsername());
        if (adminList!=null && adminList.size()>0){
            return adminList.get(0);
        }
        return null;
    }

    //用户登录
    @Override
    public String login(String username, String password) {
        String token=null;
        try{
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);//生成Token
            //插入登录记录
//            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        }catch (AuthenticationException e){
            LOGGER.warn("登录异常:{}",e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }


    //检查用户信息
    @Override
    public UserDetails loadUserByUsername(String username) {
        //获取用户信息
        UserAdmin admin = getAdminByUsername(username);
        if (admin!=null){
            List<UserPermission> permissionList=getPermissionList(admin.getId());
            return new AdminUserDetails(admin,permissionList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    //获取权限列表
    @Override
    public List<UserPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    /**
     * 添加的登录记录
     */
    private void insertLoginLog(String username){
        UserAdmin admin = getAdminByUsername(username);
        UserAdminLoginLog loginLog = new UserAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);//插入登录记录
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        UserAdmin record = new UserAdmin();
        record.setLoginTime(new Date());
        record.setUsername(username);
        userAdminMapper.updateUserLoginTimeByUserName(record.getLoginTime(),record.getUsername());
    }
}
