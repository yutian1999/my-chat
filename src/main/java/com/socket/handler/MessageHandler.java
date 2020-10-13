/**
 * aljk.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.socket.handler;

import com.alibaba.fastjson.JSON;
import com.socket.session.SocketSession;
import com.socket.transfer.TransferMessage;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;

/**
 *
 * @author wengyz
 * @version MessageHandler.java, v 0.1 2020-10-13 8:16 下午
 */
@Slf4j
public class MessageHandler {

    /**
     * 消息分发
     * @param msg 消息
     * @param session session
     */
    public static void msgSender(String msg, Session session){
        String sender = SocketSession.getUserId(session);
        Session senderSession = SocketSession.getSession(sender);
        if (null == senderSession){
            log.warn("msgSender >> warning sender offLine");
            SocketSession.close(session);
            return;
        }

        TransferMessage message = JSON.parseObject(msg, TransferMessage.class);
        Session write = SocketSession.getSession(message.getReceiver());
        if (null == write){
            log.info("msgSender >>  接收者不在线 receiver = {}",message.getReceiver());
            return;
        }
        message.setReceiver(message.getReceiver());
        message.setSender(sender);
        write.getAsyncRemote().sendText(JSON.toJSONString(message));
    }
}