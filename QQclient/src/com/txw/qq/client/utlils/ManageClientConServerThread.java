package com.txw.qq.client.utlils;

import java.util.HashMap;
/**
 * 这是一个管理客户端和服务器端保持通讯的线程类
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class ManageClientConServerThread {
   private static HashMap<String,ClientConServerThread> map = new HashMap<>();  //创建map集合
    /**
     *吧创建好的ClientConServerThread放入到map集合中
     * @param qqId
     * @param ccst
     */
    public static void addClientConServerThread(String qqId, ClientConServerThread ccst){
        map.put(qqId,ccst);
    }
    /**
     *可以通过QQId取得该线程
     * @param qqId
     * @return
     */
    public static  ClientConServerThread getClientConServerThread(String qqId){
        return (ClientConServerThread)map.get(qqId);
    }
}