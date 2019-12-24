package com.gantch.nbiotdevicesmanagement.config;

import com.gantch.nbiotdevicesmanagement.service.UserAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author lcw332
 * Date 2019-12-18-13:12
 * Description:  nbiot-devies-management , com.gantch.nbiotdevicesmanagement.config
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
