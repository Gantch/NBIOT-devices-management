package com.gantch.nbiotmanagement.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author lcw332
 * Date 2019-12-17-14:07
 * Description:  nbiot-devices-management , com.gantch.devicesmanagement.config
 * MyBatis配置类
 **/
@Configuration
@EnableTransactionManagement
@MapperScan({"com.gantch.nbiotmanagement.mapper","com.gantch.nbiotmanagement.dao"})
public class MyBatisConfig {
}
