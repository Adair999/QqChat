package com.txw.qq.client.utlils;

import com.txw.qq.client.view.QqChat;
import java.util.HashMap;
/**
 * 这是一个管理用户聊天界面的类
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")  //注解警告信息
public class ManageQqChat {
    //显示唯一聊天窗口
    public static HashMap<String,QqChat> map = new HashMap();  //创建map集合
    /**
     *把用户聊天界面qqChat添加到map中
     * @param loginOwnerIdAndFriendId
     * @param qqChat
     */
    public static void addQqChat(String loginOwnerIdAndFriendId, QqChat qqChat){
        map.put(loginOwnerIdAndFriendId,qqChat);
    }
    /**
     * 根据登入用户和发送用户取得该聊天界面
     * @param loginOwnerIdAndFriendId
     * @return
     */
    public static QqChat getQqChat(String loginOwnerIdAndFriendId){
        return (QqChat) map.get(loginOwnerIdAndFriendId);
    }
}