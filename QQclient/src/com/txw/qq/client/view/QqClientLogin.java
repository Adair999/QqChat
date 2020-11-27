package com.txw.qq.client.view;

import com.txw.qq.client.modal.QqClientUser;
import com.txw.qq.client.utlils.ManageClientConServerThread;
import com.txw.qq.client.utlils.ManageQqFriendList;
import com.txw.qq.common.Message;
import com.txw.qq.common.MessageType;
import com.txw.qq.common.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 * QQ聊天器
 * QQ客户端登录界面
 */
@SuppressWarnings("all")  //注解警告信息
public class QqClientLogin extends JFrame implements ActionListener {
    //获取当前平面分辨率，例如：1920x1080
    int swidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int sheight = Toolkit.getDefaultToolkit().getScreenSize().height;
    //定义北部需要的组件
    JLabel jLabel;
    //定义中部需要的组件
    //中部有两个输入框和三个选择框
    JPanel jPanel1;
    JLabel jPanel1_jLabel1,jPanel1_jLabel2;
    JButton jPanel1_jButton ,jPanel1_jButton1;
    JTextField jPanel1_jtf1;
    JPasswordField jPanel1_jpf1;
    JCheckBox jPanel1_jcb1,jPanel1_jcb2,jPanel1_jcb3;
    //定义南部需要的组件
    JPanel jPanel;
    JButton jButton1,jButton2,jButton3;
    public QqClientLogin()  {
        //处理北部
        jLabel = new JLabel(new ImageIcon("image/proxy.gif"));
        //处理中部
        jPanel1 = new JPanel(new GridLayout(3,3));
        jPanel1_jLabel1 = new JLabel("QQ号:",JLabel.RIGHT);
        jPanel1_jLabel2 = new JLabel("QQ密码:",JLabel.RIGHT);
        jPanel1_jButton1 = new JButton("忘记密码");
        jPanel1_jButton1.setForeground(Color.BLUE);
        jPanel1_jButton = new JButton("清除QQ号");
        jPanel1_jButton.setForeground(Color.BLUE);
        jPanel1_jtf1 = new JTextField(JTextField.CENTER);
        jPanel1_jpf1 = new JPasswordField();
        jPanel1_jcb1 = new JCheckBox("自动登录");
        jPanel1_jcb2 = new JCheckBox("记住密码");
        jPanel1_jcb3 = new JCheckBox("找回密码");
        //把控件安装顺序加入到jPanel1中
        jPanel1.add(jPanel1_jLabel1);
        jPanel1.add(jPanel1_jtf1);
        jPanel1.add(jPanel1_jButton1);
        jPanel1.add(jPanel1_jButton);
        jPanel1.add(jPanel1_jLabel2);
        jPanel1.add(jPanel1_jpf1);
        jPanel1.add(jPanel1_jButton1);
        jPanel1.add(jPanel1_jcb1);
        jPanel1.add(jPanel1_jcb2);
        jPanel1.add(jPanel1_jcb3);
        //处理南部
        jPanel = new JPanel();
        jButton1 = new JButton(new ImageIcon("image/zhucezhabghao.gif"));
        jButton2 = new JButton(new ImageIcon("image/anquandenglu.gif"));
        //响应用户点击登录
        jButton2.addActionListener(this);
        jButton3 = new JButton(new ImageIcon("image/erweima.gif"));
        //把三个按钮放入到jPanel
        jPanel.add(jButton1);
        jPanel.add(jButton2);
        jPanel.add(jButton3);
        this.add(jLabel,"North");  //把jLabel放在北面
        this.add(jPanel1,"Center");//把jLabel1放在中间
        this.add(jPanel,"South"); //把jPanel放在南面
        int width = 370, height = 360;
        this.setSize(width, height);
        //设置窗体出现的位置
        this.setLocation((swidth - width) / 2, (sheight - height) / 2);
        this.setResizable(false); //窗口大小不可变
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置默认关闭按钮
        this.setVisible(true);     //设置窗体可见

    }
    public static void main(String[] args) {
        QqClientLogin qqClientLogin = new QqClientLogin();  //创建qqClientLogin对象
    }
    /**
     *点击触发事件
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果用户点击登录
        if (e.getSource() == jButton2){
            QqClientUser qqClientUser = new QqClientUser();   //创建QqClientUser实例对象
            User u = new User();   //创建User实例对象
            u.setUserId(jPanel1_jtf1.getText().trim());  //设置账户
            u.setPassword(new String(jPanel1_jpf1.getPassword()));   //设置密码
            //判断用户是否登录成功
            if (qqClientUser.checkUser(u)){
                try {
                    //把创建好友列表提前
                    QqFriendList qqList = new QqFriendList(u.getUserId());
                    ManageQqFriendList.addQqFriendList(u.getUserId(),qqList);
                    //发送一个要求返回好友在线的信息包
                    ObjectOutputStream oos = new ObjectOutputStream(
                            ManageClientConServerThread.getClientConServerThread(u.getUserId()).getS().getOutputStream());
                    //做一个Message包
                    Message m = new Message();
                    m.setMesType(MessageType.message_get_onlineFriend);
                    m.setSender(u.getUserId());  //要的是这个QQ号的好友情况
                    oos.writeObject(m);  //发送
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                // 同时关闭登录界面
                this.dispose();
            }else {
                //提示信息
                JOptionPane.showMessageDialog(this,"用户名或密码错误");
            }
        }
    }
}