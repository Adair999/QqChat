package com.txw.qq.client.view;

import com.txw.qq.client.utlils.ManageQqChat;
import com.txw.qq.common.Message;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 * 我的好友列表(也包括陌生人，黑名单)
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")  //注解警告信息
public class QqFriendList extends JFrame implements ActionListener,MouseListener{
    //获取当前平面分辨率，例如：1920x1080
    int swidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int sheight = Toolkit.getDefaultToolkit().getScreenSize().height;
    String owner;  //定义自己的id
    //处理第一张卡片（我的好友）
    JPanel jhy1,jhy2,jhy3;
    //三个按钮
    JButton jhy_jb1,jhy_jb2,jhy_jb3;
    JScrollPane jp1;  //滚动条
    JLabel[] jLb1,jLb2,jLb3;
    //处理第二张卡片(陌生人)
    JPanel jmsr1,jmsr2,jmsr3;
    //三个按钮
    JButton jmsr_jb1,jmsr_jb2,jmsr_jb3;
    JScrollPane jp2;  //滚动条
    // 处理第三张卡片(黑名单)
    JPanel jhmd1,jhmd2,jhmd3;
    //三个按钮
    JButton jhmd_jb1,jhmd_jb2,jhmd_jb3;
    JScrollPane jp3;  //滚动条
    //把整个JFrame设置成cardLayout
    CardLayout cardLayout;
    //处理第一张卡片（我的好友）
    public void firstCard(){
        jhy_jb1 = new JButton("我的好友");
        jhy_jb2 = new JButton("陌生人");
        jhy_jb2.addActionListener(this);
        jhy_jb3 = new JButton("黑名单");
        jhy1 = new JPanel(new BorderLayout());
        //假定有50个好友并且数设置行间距和列间距为4
        jhy2 = new JPanel(new GridLayout(50,1,4,4));
        //给jhy2初始化50个好友
        jLb1 = new JLabel[50];
        for (int i = 0; i < jLb1.length; i++) {
            jLb1[i] = new JLabel( i + 1 +"", new ImageIcon("image/mm.jpeg"),JLabel.LEFT);
            jLb1[i].setEnabled(false);  //默认不在线
            if(jLb1[i].getText().equals(owner)){
                jLb1[i].setEnabled(true);  //在线
            }
            jLb1[i].addMouseListener(this);
            jhy2.add(jLb1[i]);
        }
        jhy3 = new JPanel(new GridLayout(2,1));
        //把两个按钮加入到jhy3中
        jhy3.add(jhy_jb2);
        jhy3.add(jhy_jb3);
        jp1 = new JScrollPane(jhy2);
        //对jhy1初始化
        jhy1.add(jhy_jb1,"North");
        jhy1.add(jp1,"Center");
        jhy1.add(jhy3,"South");
        this.add(jhy1,"Center");
    }
    //处理第二张卡片( 显示陌生人)
    public void secondCard(){
        jmsr_jb1 = new JButton("我的好友");
        //jmsr_jb1.addActionListener(this);
        jmsr_jb2 = new JButton("陌生人");
        jmsr_jb3 = new JButton("黑名单");
        jmsr_jb3.addActionListener(this);
        jmsr1 = new JPanel(new BorderLayout());
        //假定有20个陌生人并且数设置行间距和列间距为4
        jmsr2 = new JPanel(new GridLayout(20,1,4,4));
        //给jmsr2初始化20个好友
        jLb2 = new JLabel[20];
        for (int i = 0; i < jLb2.length; i++) {
            jLb2[i] = new JLabel( i + 1 +"", new ImageIcon("image/mm.jpeg"),JLabel.LEFT);
            jLb2[i].setEnabled(false);   //设置默认不在线
            if (jLb2[i].getText().equals(owner)){
                jLb2[i].setEnabled(true);  //设置在线
            }
            jLb2[i].addMouseListener(this);
            jmsr2.add(jLb2[i]);
        }
        jmsr3 = new JPanel(new GridLayout(2,1));
        //把两个按钮加入到jmsr3中
        jmsr3.add(jmsr_jb1);
        jmsr3.add(jmsr_jb2);
        jp2 = new JScrollPane(jmsr2);
        //对jmsr1初始化
        jmsr1.add(jmsr3,"North");
        jmsr1.add(jp2,"Center");
        jmsr1.add(jmsr_jb3,"South");
    }
    //处理第三张卡片( 显示黑名单)
    public void thirdCard(){
        jhmd_jb1 = new JButton("我的好友");
        jhmd_jb1.addActionListener(this);
        jhmd_jb2 = new JButton("陌生人");
        jhmd_jb3 = new JButton("黑名单");
        jhmd_jb3.addActionListener(this);
        jhmd1 = new JPanel(new BorderLayout());
        //假定有10个黑名单并且数设置行间距和列间距为4
        jhmd2 = new JPanel(new GridLayout(10,1,4,4));
        //给jp3初始化20个好友
        jLb3 = new JLabel[10];
        for (int i = 0; i < jLb3.length; i++) {
            jLb3[i] = new JLabel( i + 1 +"", new ImageIcon("image/mm.jpeg"),JLabel.LEFT);
            jLb3[i].setEnabled(false);  //设置默认不在线
            if (jLb3[i].getText().equals(owner)){
                jLb3[i].setEnabled(true);  //设置在线
            }
            jLb3[i].addMouseListener(this);
            jhmd2.add(jLb3[i]);
        }
        jhmd3 = new JPanel(new GridLayout(2,1));
        //把两个按钮加入到jhmd3中
        jhmd3.add(jhmd_jb1);
        jhmd3.add(jhmd_jb3);
        jp3 = new JScrollPane(jhmd2);
        //对jmsr1初始化
        jhmd1.add(jhmd3,"North");
        jhmd1.add(jp3,"Center");
        jhmd1.add(jhmd_jb2,"South");
    }
    //创建构造方法
    public QqFriendList(String ownerId) {
        this.owner = ownerId;
        //处理第一张卡片(显示好友列表)
        firstCard();
        //处理第二张卡片(显示陌生人)
        secondCard();
        //处理第三张卡片(显示黑名单)
        thirdCard();
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.add(jhy1,"1");
        this.add(jmsr1,"2");
        this.add(jhmd1,"3");
        //在窗口显示自己的编号
        this.setTitle(ownerId);
        int width = 180, height = 400;
        this.setSize(width, height);
        //设置窗体出现的位置
        this.setLocation((swidth - width) / 2, (sheight - height) / 2);
        this.setResizable(false); //窗口大小不可变
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置默认关闭按钮
        this.setVisible(true);     //设置窗体可见
    }
    /*
     * public static void main(String[] args) { QqFriendList qqFriendList = new
     * QqFriendList(); //创建QqFriendList实例对象 qqFriendList.setTitle("好友列表"); }
     */
    /**
     *按钮单击触发事件功能
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果点击了陌生人的按钮，就显示第二张卡片
        if (e.getSource() == jhy_jb2){
            cardLayout.show(this.getContentPane(),"2");
            //如果点击了我的好友的按钮，就显示第一张卡片
        }else if (e.getSource() == jmsr_jb1) {
            cardLayout.show(this.getContentPane(), "1");
            //如果点击了我的好友的按钮，就显示第三张卡片
        }else if(e.getSource() == jhmd_jb3) {
            cardLayout.show(this.getContentPane(), "3");
        }else if(e.getSource() == jmsr_jb3){
            cardLayout.show(this.getContentPane(), "3");
        }else if(e.getSource() == jhmd_jb1){
            cardLayout.show(this.getContentPane(), "1");
        }
    }
    /**
     *更新在线好友的情况
     * @param m
     */
    public void updateFriend(Message m){
        String[] onlineFriend = m.getCon().split(" ");
        for (int i = 0; i < onlineFriend.length; i++) {
                jLb1[Integer.parseInt(onlineFriend[i]) -1].setEnabled(true);
                /*jLb2[Integer.parseInt(onlineFriend[i]) -1].setEnabled(true);
                jLb3[Integer.parseInt(onlineFriend[i]) -1].setEnabled(true);*/
        }
    }
    /**
     * 鼠标点击事件
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        //响应用户的双击事情，并得到好友的编号
        if (e.getClickCount() ==2){
            //得到好友的编号
            String friendNo = ((JLabel) e.getSource()).getText();
            // System.out.println("你希望和" + friendNo +"聊天！");
            QqChat qqChat =  new QqChat( this.owner,friendNo);
            /*Thread t = new Thread(qqChat);  //创建Thread对象并传qqChat
            t.start();  //开启线程*/
            //把聊天界面加入到管理类中
            ManageQqChat.addQqChat(this.owner +" "+ friendNo,qqChat);
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    /**
     * 鼠标进入事件
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel jLabel  =(JLabel) e.getSource();
        jLabel.setForeground(Color.blue);   //设置前景色
    }
    /**
     * 鼠标退出事件
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        JLabel jLabel  =(JLabel) e.getSource();
        jLabel.setForeground(Color.black);   //设置前景色
    }
}