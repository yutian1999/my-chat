/**
 * aljk.com
 * Copyright (C) 2013-2020 All Rights Reserved.
 */
package com.socket.session;

import org.springframework.util.StringUtils;

import javax.websocket.Session;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author wengyz
 * @version SocketSession.java, v 0.1 2020-10-13 4:38 下午
 */
public class SocketSession implements Serializable {

    private static final Map<String,Session> sessions = new HashMap<>();

    /**
     * 获取session
     * @param userId 用户id
     * @return session
     */
    public static Session getSession(String userId){
        return sessions.get(userId);
    }

    /**
     * 上线
     * @param session session
     * @return userId
     */
    public static String onLine(Session session) {
        String userId = getUserId(session);
        if (userId == null){
            close(session);
            return null;
        }
        onLine(session,userId);
        return userId;
    }

    /**
     * 下线
     * @param session session
     * @return userId
     */
    public static String offLine(Session session) {
        String userId = getUserId(session);
        if (userId == null){
            session.getAsyncRemote().sendText("【缺少必要参数】关闭连接失败");
            return null;
        }
        sessions.remove(userId);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userId;
    }


    /**
     * 获取用户id
     * @param session session
     * @return userId
     */
    public static String getUserId(Session session) {
        String info = session.getQueryString();
        String[] params = info.split("&");
        if (params.length == 0){
            return null;
        }
        String[] userInfo = params[0].split("=");

        if (userInfo.length < 2){
            return null;
        }

        if (!userInfo[0].equals("userId")){
            return null;
        }

        String userId = userInfo[1];
        if (StringUtils.isEmpty(userId)){
            return null;
        }

        return userId;
    }

    private static void onLine(Session session, String userId) {
        /*if (sessions.containsKey(userId)){
            Session login = sessions.get(userId);
            try {
                login.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        sessions.put(userId,session);
        session.getAsyncRemote().sendText("hello  " + userId);
    }

    public static void close(Session session) {
        session.getAsyncRemote().sendText("【缺少必要参数】连接失败");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}