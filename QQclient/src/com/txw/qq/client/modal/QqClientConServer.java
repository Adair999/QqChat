package com.txw.qq.client.modal;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.txw.qq.client.utlils.ClientConServerThread;
import com.txw.qq.client.utlils.ManageClientConServerThread;
import com.txw.qq.common.Message;
import com.txw.qq.common.User;
/**
 * 这是连接服务器的后台
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings({"all", "AliEqualsAvoidNull"})   //注解警告信息
public class QqClientConServer {
    //实现共享 Socket
    public Socket s;
    /**
     * 判断是否登录成功，成功返回true，否则返回false
     * @param o
     * @return
     */
    public boolean sendLoginInfoToServer(Object o) {
        boolean b  = false;  //定义boolean类型变量b并初始化为false
        try {
            s  = new Socket("localhost",9999);  //创建Socket对象，并设置IP地址和端口号
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(o);  // 写出去
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            Message ms = (Message) ois.readObject();//读取
            //这里就是验证用户登录的地方
            if ("1".equals(ms.getMesType())){
                b = true;   //表明登录成功
                //就是创建一个该qq号与服务器端保持连接的通讯线程
                ClientConServerThread  ccst = new ClientConServerThread(s);
                //把改线程添加到管理线程map中
                ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(),ccst);
                //启动该通讯线程
                new Thread(ccst).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
        return b;
    }
}