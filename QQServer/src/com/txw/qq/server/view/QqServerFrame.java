package com.txw.qq.server.view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.txw.qq.server.modal.QqServer;
/**
 * 这是服务器端的控制界面，可以完成启动服务器，关闭服务器
 * 可以管理和监控用户。
 * @author 唐兴旺
 *@version 1.0
 */
@SuppressWarnings("all")   //注解警告信息
public class QqServerFrame extends JFrame implements ActionListener {
    //获取当前平面分辨率，例如：1920x1080
    int swidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    int sheight = Toolkit.getDefaultToolkit().getScreenSize().height;
    JPanel jPanel;  //面板
    JButton jb1, jb2;  //两个按钮
    //创建构造方法
    public QqServerFrame(){
        jPanel = new JPanel();
        jb1 = new JButton("启动服务器");
        jb1.addActionListener(this);
        jb2 = new JButton("关闭服务器");
        jPanel.add(jb1);  //把两个按钮放进面板中
        jPanel.add(jb2);
        this.add(jPanel);  //把jPanel窗体中
        this.setTitle("服务器窗口");  //设置标题
        int width = 500,height = 400;
        this.setSize(width, height);
        //设置窗体出现的位置
        this.setLocation((swidth - width) / 2, (sheight - height) / 2);
        this.setResizable(false); //窗口大小不可变
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置默认关闭按钮
        this.setVisible(true);     //设置窗体可见
    }
    public static void main(String[] args) {
        QqServerFrame qqServerFrame = new QqServerFrame();   //创建QqServerFrame实例对象
    }
    /**
     * 监听事件
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1){   //jb1是否点击启动服务器成功
            new QqServer().sever();
        }
    }
}