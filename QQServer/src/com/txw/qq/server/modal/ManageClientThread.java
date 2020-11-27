package com.txw.qq.server.modal;

import java.util.HashMap;
/**
 * 管理客户端的通讯的线程
 * @author 唐兴旺
 * @version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class ManageClientThread {
	//管理者主要有一个map集合，就可以
	public static HashMap map = new HashMap<String, SerConClientThread>();  //创建map集合
	/**
	 * 向map集合中添加一个客户端通讯线程
	 * @param uid
	 * @param scct
	 */
	public static void addClientThread(String uid,SerConClientThread ct ) {
		map.put(uid, ct);
	}
	/**
	 * 通过uid得到一个线程
	 * @param uid
	 * @return
	 */
	public static SerConClientThread getClientThread(String uid) {
		return (SerConClientThread) map.get(uid);
	}
	/**
	 * 返回当前在线人的情况
	 * @return
	 */
	public static String getAllOnlineUserId(){
		String res = "";
		for (Object o : map.keySet()) {
			res += o.toString() +" ";
		}
		return  res;
	}
}