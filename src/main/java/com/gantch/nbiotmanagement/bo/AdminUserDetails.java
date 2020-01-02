package com.gantch.nbiotmanagement.bo;

import com.gantch.nbiotmanagement.pojo.UserAdmin;
import com.gantch.nbiotmanagement.pojo.UserPermission;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lcw332
 * Date 2019-12-26-11:37
 * Description:  nbiot-management , com.gantch.nbiotmanagement.bo
 * SpringSecurity需要的用户详情
 **/
public class AdminUserDetails implements UserDetails {
    private UserAdmin userAdmin;
    private List<UserPermission> permissionList;

    public AdminUserDetails(UserAdmin userAdmin,List<UserPermission> permissionList){
        this.userAdmin=userAdmin;
        this.permissionList=permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream().filter(permission ->permission.getValue()!=null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());

    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAdmin.getStatus().equals(1);
    }
}
