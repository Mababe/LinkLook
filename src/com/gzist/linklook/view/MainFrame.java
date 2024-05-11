package com.gzist.linklook.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    final int width = 600; // 设置窗口宽度
    final int height = 600; // 设置窗口高度

    public static int rows = 6; // 连连看的行数
    public static int cols = 6; // 连连看的列数
    // 生成多个按钮
    JButton[][] buttons = new JButton[rows][cols];

    public MainFrame() {
        // 获取屏幕大小
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); // 屏幕分辨率
        this.setTitle("连连看"); // 设置标题
//		this.resize(width + 7, height + 54);
        this.setBounds(1, 1, width + 17, height + 38); // 设置大小
        this.setLocation((screenSize.width - width) / 2,
                (screenSize.height - height) / 2); // 居中显示
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭模式
        this.setResizable(false); // 设置不允许拉伸窗口

        // 添加菜单栏
        JMenuBar menubar = new JMenuBar();
        this.setJMenuBar(menubar); // 设置菜单栏
        JMenu menu1 = new JMenu("文件");
        JMenuItem mi1 = new JMenuItem("重新开始");
        JMenuItem mi3 = new JMenuItem("退出");
        menubar.add(menu1); // 添加菜单栏
        menu1.add(mi1);
        menu1.add(mi3);

        /**
         * 设置菜单栏监听事件
         */
        mi1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("重新开始");
            }
        });
        mi3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("退出");
            }
        });

        initPane(); // 设置面板
        initButton(); // 初始化按钮
        showImage(); // 显示图片
    }
    /**
     * 面板，显示在窗口上方
     */
    private void initPane() {
        JPanel scorePane = new JPanel(new FlowLayout()); // 设置成流体布局
        scorePane.add(new JLabel("用户名：hui"));
        scorePane.add(new JLabel("    "));
        scorePane.add(new JLabel("得分：0"));

        JButton btnResort = new JButton("重排");
        JButton btnReplay = new JButton("新游戏");
        btnResort.setContentAreaFilled(false);
        btnResort.setBorderPainted(false);


        scorePane.add(btnResort);
        scorePane.add(btnReplay);
        this.getContentPane().add(scorePane, BorderLayout.NORTH); // 添加此在面板上面
    }
    /**
     * 初始化面板上面的按钮。
     */
    public void initButton() {
        JPanel gamePanel = new JPanel(new GridLayout(rows, cols)); // 设置成网格布局
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols ; ++j) {
                buttons[i][j] = new JButton(); // 生成按钮
                gamePanel.add(buttons[i][j]);
                // 居中显示
                this.getContentPane().add(gamePanel, BorderLayout.CENTER);
            }
    }
    /**
     * 显示图片
     */
    private void showImage() {
        for (int i = 0; i < rows ; ++i) {
            for (int j = 0; j < cols ; ++j) {
                buttons[i][j].setVisible(true);
//				String path = this.getClass().getResource("/image").getPath();
                // 获取图片路径
                ImageIcon icon = new ImageIcon("src/images/1.png");
//				System.out.println(icon);
                // 设置图片大小
                icon.setImage(icon.getImage().getScaledInstance(
                        60, 60,
                        Image.SCALE_DEFAULT));
                buttons[i][j].setIcon(icon);
            }
        }
    }
}
