package com.gzist.linklook.view;

import com.gzist.linklook.data.Users;
import com.gzist.linklook.data.UsersService;
import com.gzist.linklook.data.UsersServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 显示登录界面
 **/
public class LoginFrame extends JFrame {
    private static final int height = 200; // 设置窗口高度
    private static final int width = 350; // 设置窗口宽度

    UsersService userSerivce = new UsersServiceImpl();

    public LoginFrame() {
        // 获取屏幕大小
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(1, 1, width + 17, height + 38);    // 设置大小
        this.setLocation((screenSize.width - width) / 2,
                (screenSize.height - height) / 2); // 居中显示
        this.setTitle("登录界面"); // 设置程序名字
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭按钮
        initPane(); // 初始化登录面板

    }

    /**
     * 初始化登录面板
     */
    private void initPane() {
        final JPanel fieldPanel = new JPanel(); // 生成panel对象
        //创建表格布局管理器
        GridLayout gridLayout = new GridLayout(4, 1, 10, 10);
        //让窗体交给表格布局管理器管理
        fieldPanel.setLayout(gridLayout);

        JLabel a1 = new JLabel("昵  称:", SwingConstants.RIGHT);
        fieldPanel.add(a1); // 添加按钮
        JTextField nickname = new JTextField(12);
        fieldPanel.add(nickname); // 添加文本框

        JLabel a2 = new JLabel("账  号:", SwingConstants.RIGHT);
        fieldPanel.add(a2);
        JTextField loginId = new JTextField(12);
        fieldPanel.add(loginId);

        JLabel a3 = new JLabel("密  码:", SwingConstants.RIGHT);
        fieldPanel.add(a3);
        JPasswordField loginPwd = new JPasswordField(12);
        fieldPanel.add(loginPwd);

        JButton jbu1 = new JButton("开始游戏"); // 生成确定按钮
        JButton jbu2 = new JButton("退出游戏"); // 生成取消按钮
        fieldPanel.add(jbu1, BorderLayout.SOUTH); // 添加按钮
        fieldPanel.add(jbu2, BorderLayout.SOUTH); // 添加按钮
        /**
         * 点击“确定”按钮
         */
        jbu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String name = nickname.getText(); //获取文本框的文本
                String id = loginId.getText();
                String pwd = String.valueOf(loginPwd.getPassword());
                System.out.println(name + " " + id + " " + pwd);
                if (!"".equals(id) && !"".equals(pwd)) {   //&&!"".equals(pwd) 文本不能为空
                    Users users = userSerivce.login(id, pwd);
                    if (users == null) {
//                    System.out.println(users);
                        System.out.println("用户名或密码不正确");
                    }else{
                        System.out.println("登陆成功");
                        setVisible(false);
                        Users.nickname = name; // 设置名字
                        MainFrame mainFrame = new MainFrame(); // 打开主界面
                        mainFrame.setVisible(true); // 设置可见
                        dispose(); // 当前界面消失
                    }
                } else {
                    System.out.println("输入不能为空！");
                }
            }
        });
        /**
         * 点击"取消"按钮
         */
        jbu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0); // 退出游戏
            }
        });
        this.getContentPane().add(fieldPanel, BorderLayout.CENTER); // 居中显示窗口
    }
}
