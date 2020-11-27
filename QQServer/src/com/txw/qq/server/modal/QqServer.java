package com.txw.qq.server.modal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import com.txw.qq.common.Message;
import com.txw.qq.common.User;
/**
 * 这是qq服务器,它在监听，等待某个qq客户端，来连接
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class QqServer {
    /**
     * 这是qq服务器,它在监听，等待某个qq客户端，来连接
     */
    public  void sever(){
        try {
            System.out.println("我是服务器，在9999监听");
            ServerSocket ss = new ServerSocket(9999);    //创建ServerSocket对象
            //阻塞，等待客户端连接
            while (true){
                Socket s = ss.accept();
                //接收客户端发来的信息
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User u = (User) ois.readObject();
                System.out.println("服务器接收到的用户id: " + u.getUserId() + "\r\n"
                        + "密码：" + u.getPassword());
                Message m = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                if ("123456".equals(u.getPassword())) {
                    //返回成功登录的信息包
                    m.setMesType("1");
                    oos.writeObject(m);
                    //这里就单开一个线程，让该线程与该客服端保持通讯
                    SerConClientThread scct = new SerConClientThread(s);
                    ManageClientThread.addClientThread(u.getUserId(), scct);
                    scct.start();   //启动与客服端通信的线程
                    //并通知其他在线用户
                    scct.notifyOther(u.getUserId());
                } else {
                    m.setMesType("2");
                    oos.writeObject(m);
                    s.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}