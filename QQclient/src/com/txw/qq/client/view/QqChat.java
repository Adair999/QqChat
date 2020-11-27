package com.txw.qq.client.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import com.txw.qq.client.utlils.ManageClientConServerThread;
import com.txw.qq.common.Message;
import com.txw.qq.common.MessageType;
/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态。所以，我们把它做成一个线程
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class QqChat extends JFrame implements ActionListener {
	//获取当前平面分辨率，例如：1920x1080
	int swidth = Toolkit.getDefaultToolkit().getScreenSize().width;
	int sheight = Toolkit.getDefaultToolkit().getScreenSize().height;
	JTextField jtf;  //输入文本框
	JTextArea jta;   //文本域
	JButton jb;       //发送按钮
	JPanel jp;   //装输入文本框和文本域面板
	String ownerId;  //定义自己的编号
	String friendId;  //定义好友的编号
	//创建构造方法
	public QqChat(String ownerId,String friend)  {
		//进行赋值
		this.ownerId = ownerId;
		this.friendId = friend;
		jtf = new JTextField(20);
		jta = new JTextArea();
		jb = new JButton("发送");
		jb.addActionListener(this);  //监听事件
		jp = new JPanel();
		jp.add(jtf);  //把文本框放进面板中
		jp.add(jb);  //把按钮放进面板中
		this.add(jta,"Center");  //把文本域添加到窗体中并且居中
		this.add(jp,"South"); //把面板添加到窗体中并且放在南部
		this.setTitle( ownerId + "正在和" + friend + "聊天中");
		this.setIconImage((new ImageIcon("image/qq.gif")).getImage());
		int width = 360,height = 360;
		this.setSize(width, height);
		//设置窗体出现的位置
		this.setLocation((swidth - width) / 2, (sheight - height) / 2);
		this.setResizable(false); //窗口大小不可变
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置默认关闭按钮
		this.setVisible(true);     //设置窗体可见
	}
	/*public static void main(String[] args) {
		//QqChat qqChat = new QqChat("1");   //创建QqChat实例对象
	}*/
	/**
	 * 写一个方法，让它显示消息
	 * @param m
	 */
	public void showMessage(Message m){
		String info = m.getSender() +"对  "+m.getGetter()+"说: "+m.getCon()+"\r\n";
		this.jta.append(info);
	}
	/**
	 *点击触发事件
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		//如果用户点击了，发送按钮
		if(e.getSource() == jb) {
			Message message = new Message();  //创建Message对象
			message.setMesType(MessageType.message_comm_mse);
			message.setSender(this.ownerId);   //设置发送者
			message.setGetter(this.friendId);  //设置接收者
			message.setCon(jtf.getText());  //设置发送的内容
			message.setSendTime(new Date().toString());  //设置发送的时间
			//发送给服务器
			try {
				ObjectOutputStream oos = new ObjectOutputStream(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(message);  //把m写进去
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	/**
	 * 重新run方法
	 */
	/*@Override
	public void run() {
		while(true) {  //循环条件
			try {
				//读取[如果读取不到就等待。]
				ObjectInputStream ois = new ObjectInputStream(QqClientConServer.s.getInputStream());
				Message m = (Message)ois.readObject();
				//显示在文本域中
				String info = m.getSender() +"对  "+m.getGetter()+"说: "+m.getCon()+"\r\n";
				this.jTextArea.append(info);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}*/
}