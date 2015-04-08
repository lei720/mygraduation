package edu.cuit.main;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import edu.cuit.bean.User;
import edu.cuit.dao.UserDao;
import edu.cuit.mainFrame.RemoveButtomFrame;

import edu.cuit.panel.ProgressPanel;
import edu.cuit.util.Session;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
public class Enter extends JFrame {
	private BackgroundPanel ConPane;
	private JTextField userNameTextField;
	private JPasswordField passwordField;
	private Point spoint;
	private ProgressPanel progressPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//把这个事件（new Runnable(设置计算器可见))添加到awt的事件处理线程当中去
		//awt的事件处理线程会按照队列的顺序依次调用每个待处理的事件来运行 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//这是设置图形界面外观的.java的图形界面外观有3种,默认是java的金属外观
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					SwingUtilities.invokeLater(new Runnable() {
						public void run() {
							Enter eter = new Enter();
							eter.setVisible(true);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Enter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);// 窗体居中
		setTitle("window");
		setBounds(100, 100, 559, 285);
		ConPane =  getLoginPanel();
		//EmptyBorder 该类提供了一个占用空间但不执行绘制的空透明边框。
		ConPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ConPane);
		ConPane.setLayout(null);
	
		
	}
	/**
	 * 初始化登录面板
	 * 
	 * @return 
	 */
	private BackgroundPanel getLoginPanel() {
		if (ConPane == null) {
			ConPane = new BackgroundPanel();// 创建登录面板对象		
			ConPane.setOpaque(false);// 面板透明
			ConPane.setImage(getToolkit().getImage(
		           getClass().getResource("lg.jpg")));
			ConPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	//	JPanel panel = new ClockPanel();
		//	panel.setBounds(377, 54, 151, 142);
		//	contentPane.add(panel);
			JLabel userName = new JLabel("用户名：");
			userName.setBounds(40, 116, 54, 15);
			ConPane.add(userName);
			userNameTextField = new JTextField();
			userNameTextField.setBounds(92, 113, 139, 25);
			ConPane.add(userNameTextField);
			userNameTextField.setColumns(10);
			JLabel passWord = new JLabel("密  码：");
			passWord.setBounds(40, 158, 54, 15);
			ConPane.add(passWord);
			passwordField = new JPasswordField();
			passwordField.setBounds(92, 155, 139, 25);
			ConPane.add(passwordField);
//		   progressPanel=new ProgressPanel();
			//ConPane.add(progressPanel);
			JButton enterButton = new JButton("");
			JButton resetButton=new JButton("");
			resetButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					passwordField.setText("");
					userNameTextField.setText("");
				}
			});
			URL url = getClass().getResource("test.png");
			URL url2 = getClass().getResource("reset.png");
			ImageIcon imageIcon = new ImageIcon(url);	
			ImageIcon imageIcon2 = new ImageIcon(url2);
		//	enterButton.setBounds(241, 87,81, 57);		
		
			enterButton.setIcon(imageIcon);
			resetButton.setIcon(imageIcon2);
			resetButton.setContentAreaFilled(false);
			enterButton.setContentAreaFilled(false);	// 取消填充区域
			resetButton.setBorder(null);
			enterButton.setBorder(null);				// 取消边框	
enterButton.addActionListener(new ActionListener() {		//按钮的单击事件
	public void actionPerformed(ActionEvent e) {
		UserDao userDao = new UserDao();					//创建保存有操作数据库类对象
		User user = userDao.getUser(userNameTextField.getText(),passwordField.getText());	//以用户添加的用户名与密码为参数调用查询用户方法
		ConPane.setVisible(true);
	
	//	setContentPane(progressPanel);
		if(user.getId()>0){					//判断用户编号是否大于0
			Session.setUser(user);			//设置Session对象的User属性值
		 //setContentPane(progressPanel);
			progressPanel=new ProgressPanel();
		//	progressPanel.setLayout(null);
			BufferedImage bimage=null;
			try {
				bimage = ImageIO.read( getClass().getResourceAsStream("lg.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		/*	try {
				bimage = ImageIO.read(new FileInputStream(""));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
			progressPanel.setBackImage(bimage);
			progressPanel.setBounds(0, 0, 559, 285);
		setContentPane(progressPanel);	
		 final RemoveButtomFrame frame = new RemoveButtomFrame();		//创建主窗体对象
		// frame.add(progressPanel);
		//	setContentPane(progressPanel);
	
		 //	progressPanel.setVisible(true);
		  final Timer timer = new Timer();
	        TimerTask tt=new TimerTask() { 
	            @Override
	            public void run() {
	               frame.setVisible(true);
	               Enter.this.dispose();
	                timer.cancel();
	            }
	        };
	        timer.schedule(tt, 3000);
			//显示主窗体
		/*JFrame jFrame=new JFrame();
		jFrame.add(progressPanel);
		jFrame.setVisible(true);*/
		// progressPanel.setVisible(true);
	//	setContentPane(progressPanel);
		///setContentPane(jFrame);
		//ConPane.add(progressPanel);		 
		//jFrame.setVisible(true);
			//	progressPanel.setVisible(false);
		//		ConPane.add(progressPanel);
		/*	try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
				
				//销毁登录窗体
		}
		else{								//如果用户输入的用户名与密码错误
			JOptionPane.showMessageDialog(getContentPane(), "用户名或密码错误");	//给出提示信息
			userNameTextField.setText("");		//用户名文本框设置为空
			passwordField.setText("");			//密码文本框设置为空
		}
	}
});
			enterButton.setBounds(253, 116, 93, 64);
			resetButton.setBounds(347, 120, 81, 57);
			ConPane.add(enterButton);
			
			JButton btnNewButton_1 = new JButton("New button");
			ConPane.add(btnNewButton_1);
			
			JButton btnNewButton = new JButton("New button");
			ConPane.add(btnNewButton);
			ConPane.add(resetButton);
			URL urlclose = getClass().getResource("close.png");
			ImageIcon imageIconclose = new ImageIcon(urlclose);
			// 添加鼠标事件监听器
			ConPane.addMouseListener(new TitleMouseAdapter());
			// 添加鼠标动作监听器
			
		}
		return ConPane;
	}

	private final class TitleMouseAdapter extends MouseAdapter implements
			Serializable {
		public void mousePressed(java.awt.event.MouseEvent e) {
			spoint = e.getPoint();
		}

	}

	
}
