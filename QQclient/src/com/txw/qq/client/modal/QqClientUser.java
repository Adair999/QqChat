package com.txw.qq.client.modal;

import com.txw.qq.common.User;
/**
 *这是qq客户端，发送用户名和密码
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class QqClientUser {
	/**
	 * 调用客户端与服务器后台的方法，返回true表示登录成功，返回false表示失败
	 * @param user
	 * @return
	 */
	public boolean checkUser(User user) {
		return new QqClientConServer().sendLoginInfoToServer(user);
	}
}