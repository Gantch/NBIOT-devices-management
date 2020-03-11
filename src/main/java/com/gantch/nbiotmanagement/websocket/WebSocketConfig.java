package com.gantch.nbiotmanagement.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @author lcw332
 * Date 2020-03-09-9:06
 * Description:  NBIotResolution-master , com.gantch.nbiot.websocket
 **/
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
