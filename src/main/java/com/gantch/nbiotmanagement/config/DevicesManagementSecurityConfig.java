package com.gantch.nbiotmanagement.config;

import com.gantch.nbiotmanagement.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lcw332
 * Date 2019-12-26-11:39
 * Description:  nbiot-management , com.gantch.nbiotmanagement.config
 * nbiot-devices-management-security相关配置
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DevicesManagementSecurityConfig extends SecurityConfig{

    @Autowired
    private UserAdminService userAdminService;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userAdminService.loadUserByUsername(username);
    }
}
