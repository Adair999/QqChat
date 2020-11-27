package com.txw.qq.common;

import java.io.Serializable;
/**
 * 用户信息类
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class User implements Serializable {
	private String userId;  //用户的id
	private String password;  //用户的密码
	/**
	 *get方法和set方法
	 */
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}