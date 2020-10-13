/**
 * aljk.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.socket.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 *
 * @author wengyz
 * @version WebSocketConfig.java, v 0.1 2020-10-13 4:15 下午
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}