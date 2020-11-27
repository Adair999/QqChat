package com.txw.qq.common;

import java.io.Serializable;

/**
 * 消息的实体类
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class Message implements Serializable{
	private String mesType;      //消息的类型
	private String sender;     //是谁
	private String getter;    //发给谁
	private String  con;      //信息内容本身
	private String sendTime;   //发送的时间
	/**
	 *get方法和set方法
	 */
	public String getMesType() {
		return mesType;
	}
	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getGetter() {
		return getter;
	}
	public void setGetter(String getter) {
		this.getter = getter;
	}
	public String getCon() {
		return con;
	}
	public void setCon(String con) {
		this.con = con;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
}