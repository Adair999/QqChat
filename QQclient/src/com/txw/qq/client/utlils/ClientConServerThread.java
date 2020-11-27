package com.txw.qq.client.utlils;

import com.txw.qq.client.view.QqChat;
import com.txw.qq.client.view.QqFriendList;
import com.txw.qq.common.Message;
import com.txw.qq.common.MessageType;
import java.io.ObjectInputStream;
import java.net.Socket;
/**
 * 这是客户端和服务器端保持通讯的线程
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class ClientConServerThread extends Thread {
    private Socket s;   //共享Socket
    //创建构造方法
    public ClientConServerThread(Socket s) {
        this.s = s;
    }
    public Socket getS() {
        return s;
    }
    public void setS(Socket s) {
        this.s = s;
    }
    /**
     * 重写run方法
     */
    @Override
    public void run() {
        //不停地读取从服务器端发来的消息
        while (true) {
            try {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Message m = (Message) ois.readObject();
               /*   System.out.println("读取从服务器端发来的消息:" + m.getSender()
                    +"给 " +m.getGetter() +"内容 " +m.getCon());*/
                if (m.getMesType().equals(MessageType.message_comm_mse)) {
                    //把从服务器获得消息，显示到该显示的聊天界面上
                   QqChat qqChat = ManageQqChat.getQqChat(m.getGetter()+" "+ m.getSender());
                   //显示消息
                    if (qqChat != null) {
                        qqChat.showMessage(m);
                    }
                } else if (m.getMesType().equals(MessageType.message_ret_onlineFriend)) {
                    System.out.println("客户端接收到：" + m.getCon());
                    // String con = m.getCon();  //返回信息内容
                    //String[] friends = con.split(" ");  //拆分
                    String getter = m.getGetter();  //接收者
                    System.out.println("getter= " + getter);
                    //修改相应的好友列表
                    QqFriendList qqFriendList = ManageQqFriendList.getQqFriendList(getter);
                    //更新在线好友
                    if (qqFriendList != null) {
                        qqFriendList.updateFriend(m);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}