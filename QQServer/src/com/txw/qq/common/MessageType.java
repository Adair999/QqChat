package com.txw.qq.common;

/**
 * 定义包的种类
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public interface MessageType {
    String message_succeed = "1";   //表示登录成功
    String message_login_fail = "2";   //表示登录失败
    String message_comm_mse = "3";  //表示普通信息包
    String  message_get_onlineFriend ="4";  //表示要求在线好友的包
    String  message_ret_onlineFriend ="5";  //表示返回在线好友的包
}