package com.gantch.nbiotdevicesmanagement.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lcw332
 * Date 2019-12-18-9:10
 * Description:  nbiot-devies-management , com.gantch.nbiotdeviesmanagement.config
 * 用于配置不需要保护的资源路径
 **/

@Setter
@Getter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {
    private List<String> urls = new ArrayList<>();
}
