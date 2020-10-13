/**
 * aljk.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.socket.web;
import com.socket.handler.MessageHandler;
import com.socket.session.SocketSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author wengyz
 * @version Web.java, v 0.1 2020-10-13 4:03 下午
 */
@Component
@ServerEndpoint("/socket")
@Slf4j
public class WebSocketServer {

    @OnOpen
    public void onOpen(Session session){
        String userId = SocketSession.onLine(session);
        log.info("webSocket open, userId = {}",userId);
    }

    @OnClose
    public void onClose(Session session){
        String userId = SocketSession.offLine(session);
        log.info("webSocket onClose,userId = {}",userId);
    }

    @OnMessage
    public void onMessage(String message,Session session){
        log.info("webSocket onMessage msg = {}",message);
        MessageHandler.msgSender(message,session);
    }

    @OnError
    public void onError(Throwable error,Session session) {
        log.error("socket error errorInfo = ",error);
    }
}