package com.txw.qq.client.utlils;

import com.txw.qq.client.view.QqFriendList;
import java.util.HashMap;
/**
 * 这是一个管理好友列表的界面
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class ManageQqFriendList {
   private static HashMap<String, QqFriendList> map = new HashMap<>();  //创建map集合存储qq号和好友列表
    /**
     *通过qqId和qqFriendList来添加好友列表
     * @param qqId
     * @param qqFriendList
     */
    public static void addQqFriendList(String qqId,QqFriendList qqFriendList){
        map.put(qqId,qqFriendList);
   }
    /**
     * 通过qqId得到好友列表
     * @param qqId
     * @return
     */
   public static QqFriendList getQqFriendList(String qqId){
        return (QqFriendList) map.get(qqId);
   }
}