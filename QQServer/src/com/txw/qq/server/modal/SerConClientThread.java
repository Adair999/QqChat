package com.txw.qq.server.modal;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import com.txw.qq.common.Message;
import com.txw.qq.common.MessageType;

/**
 * 是服务器和某个客户端的通讯的线程
 * @author 唐兴旺
 * @version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class SerConClientThread extends Thread{
	Socket s;  //定义Socket对象
	//创建构造方法
	public SerConClientThread(Socket s) {
		this.s = s;   //把服务器和客户端的连接赋给s
	}
	/**
	 * 让该线程去通知其他在线用户
	 * @param iam
	 */
	public void notifyOther(String iam){
		//得到所有在线人的线程
		HashMap map= ManageClientThread.map;
		for (Object o : map.keySet()) {
			Message m = new Message();
			m.setCon(iam);
			m.setMesType(MessageType.message_ret_onlineFriend);
			//取出在线人的id
			String onlineUserId  = o.toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientThread.getClientThread(onlineUserId).s.getOutputStream());
				m.setGetter(onlineUserId);
				oos.writeObject(m);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//重写run()方法
	@Override
	public void run() {
		while(true) {      //循环条件
			try {
				//这里该线程就可以接收客户端的信息
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m =(Message) ois.readObject();  //读取消息
				System.out.println(m.getSender()+"给"+ m.getGetter()+"说：" + m.getCon());
				//从客户端取得的消息进行类型判断，然后做相应的处理
				if (m.getMesType().equals(MessageType.message_comm_mse)) {
					//取得接收人的体现线程,完成转发
					SerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					oos.writeObject(m);  //把m写出去
				}else if (m.getMesType().equals(MessageType.message_get_onlineFriend)){
					System.out.println(m.getSender()+"要他的好友。");
					//把在服务器端的好友给该客户端返回
					String res = ManageClientThread.getAllOnlineUserId();
					Message m2 = new Message();
					m2.setMesType(MessageType.message_ret_onlineFriend);
					m2.setCon(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}