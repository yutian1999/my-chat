/**
 * aljk.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.socket.transfer;

import com.socket.enums.MessageTypeEnum;

/**
 *
 * @author wengyz
 * @version TransferMessage.java, v 0.1 2020-10-13 8:06 下午
 */
public class TransferMessage {
    /**
     * 发送者
     */
    private String sender;
    /**
     * 接收者
     */
    private String receiver;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息类型
     */
    private MessageTypeEnum msgType;

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MessageTypeEnum getMsgType() {
        return msgType;
    }

    public void setMsgType(MessageTypeEnum msgType) {
        this.msgType = msgType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}