package edu.cuit.subframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;

import edu.cuit.bean.Provide;
import edu.cuit.dao.FeelDao;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.Color;
public class UpdateProvideFrame extends JFrame {

	private JPanel contentPane;
	private JTextField cNameTextField;
	private JTextField addressTextField;
	private JLabel linkNameLabel;
	private JTextField linkNameTextField;
	private JLabel linkPhoneLabel;
	private JTextField linkPhoneTextField;
	private JLabel fexesLabel;
	private JTextField faxesTextField;
	private JLabel postNumLabel;
	private JTextField postNumTextField;
	private JLabel bankNumLabel;
	private JTextField bankNumTextField;
	private JLabel netAddressLabel;
	private JTextField netAddressTextField;
	private JLabel emailLabel;
	private JTextField emaillTextField;
	private JLabel label;
	private JButton closeButton;
	JTextArea remarkTextArea = new JTextArea();
	Provide provide = null;
	/**
	 * Create the frame.
	 */
	public UpdateProvideFrame() {
		setTitle("修改供应商窗体");	
		
		FeelDao dao = new FeelDao();
		 try {
			  File file = new File("file.txt");
	            FileInputStream fin = new FileInputStream(file);
	            int count =  fin.read();	           
	            file.delete();
	            provide = dao.selectProvideByid(count);
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	
		setBounds(100, 100, 635, 475);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(160, 82, 45));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel cNameLabel = new JLabel("客户名称：");
		cNameLabel.setBounds(49, 53, 72, 15);
		contentPane.add(cNameLabel);

		cNameTextField = new JTextField();
		cNameTextField.setBackground(new Color(210, 105, 30));
		cNameTextField.setText(provide.getcName());
		cNameTextField.setBounds(114, 50, 164, 25);
		contentPane.add(cNameTextField);
		cNameTextField.setColumns(10);

		JLabel addressLabel = new JLabel("客户地址：");
		addressLabel.setBounds(315, 53, 72, 15);
		contentPane.add(addressLabel);

		addressTextField = new JTextField();
		addressTextField.setBackground(new Color(210, 105, 30));
		addressTextField.setText(provide.getAddress());
		addressTextField.setColumns(10);
		addressTextField.setBounds(385, 50, 164, 25);
		contentPane.add(addressTextField);

		linkNameLabel = new JLabel(" 联系人：");
		linkNameLabel.setBounds(49, 97, 70, 15);
		contentPane.add(linkNameLabel);

		linkNameTextField = new JTextField();
		linkNameTextField.setBackground(new Color(210, 105, 30));
		linkNameTextField.setText(provide.getLinkman());
		linkNameTextField.setBounds(114, 94, 164, 25);
		contentPane.add(linkNameTextField);
		linkNameTextField.setColumns(10);

		linkPhoneLabel = new JLabel("联系电话：");
		linkPhoneLabel.setBounds(315, 97, 72, 15);
		contentPane.add(linkPhoneLabel);

		linkPhoneTextField = new JTextField();
		linkPhoneTextField.setBackground(new Color(210, 105, 30));
		linkPhoneTextField.setText(provide.getLinkPhone());
		linkPhoneTextField.setBounds(385, 94, 164, 25);
		contentPane.add(linkPhoneTextField);
		linkPhoneTextField.setColumns(10);

		fexesLabel = new JLabel("  传 真：");
		fexesLabel.setBounds(50, 140, 54, 15);
		contentPane.add(fexesLabel);

		faxesTextField = new JTextField();
		faxesTextField.setBackground(new Color(210, 105, 30));
		faxesTextField.setText(provide.getFaxes());
		faxesTextField.setColumns(10);
		faxesTextField.setBounds(114, 137, 164, 25);
		contentPane.add(faxesTextField);

		postNumLabel = new JLabel("  邮 编：");
		postNumLabel.setBounds(321, 140, 54, 15);
		contentPane.add(postNumLabel);

		postNumTextField = new JTextField();
		postNumTextField.setBackground(new Color(210, 105, 30));
		postNumTextField.setText(provide.getPostNum());
		postNumTextField.setColumns(10);
		postNumTextField.setBounds(385, 137, 164, 25);
		contentPane.add(postNumTextField);

		bankNumLabel = new JLabel("银行账号：");
		bankNumLabel.setBounds(49, 180, 72, 15);
		contentPane.add(bankNumLabel);

		bankNumTextField = new JTextField();
		bankNumTextField.setBackground(new Color(210, 105, 30));
		bankNumTextField.setText(provide.getBankNum());
		bankNumTextField.setColumns(10);
		bankNumTextField.setBounds(114, 177, 164, 25);
		contentPane.add(bankNumTextField);

		netAddressLabel = new JLabel("   网 址：");
		netAddressLabel.setBounds(315, 180, 60, 15);
		contentPane.add(netAddressLabel);

		netAddressTextField = new JTextField();
		netAddressTextField.setBackground(new Color(210, 105, 30));
		netAddressTextField.setText(provide.getNetAddress());
		netAddressTextField.setColumns(10);
		netAddressTextField.setBounds(385, 177, 164, 25);
		contentPane.add(netAddressTextField);

		emailLabel = new JLabel("   邮 箱：");
		emailLabel.setBounds(49, 223, 72, 15);
		contentPane.add(emailLabel);

		emaillTextField = new JTextField();
		emaillTextField.setBackground(new Color(210, 105, 30));
		emaillTextField.setText(provide.getEmaillAddress());
		emaillTextField.setColumns(10);
		emaillTextField.setBounds(114, 220, 164, 25);
		contentPane.add(emaillTextField);

		label = new JLabel("   备 注：");
		label.setBounds(49, 294, 72, 15);
		contentPane.add(label);
		remarkTextArea.setBackground(new Color(210, 105, 30));

		remarkTextArea.setBounds(114, 267, 435, 89);
		contentPane.add(remarkTextArea);
		remarkTextArea.setText(provide.getRemark());
		JButton uodatetButton = new JButton("修改");
		uodatetButton.setBackground(new Color(210, 105, 30));
		uodatetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeelDao dao = new FeelDao();				
				provide.setcName(cNameTextField.getText());
				provide.setAddress(addressTextField.getText());
				provide.setLinkman(linkNameTextField.getText());
				provide.setLinkPhone(linkPhoneTextField.getText());
			
				provide.setBankNum(bankNumTextField.getText());
				provide.setFaxes(faxesTextField.getText());
				provide.setPostNum(postNumTextField.getText());
				provide.setBankNum(bankNumTextField.getText());
				provide.setNetAddress(netAddressTextField.getText());
				provide.setEmaillAddress(emaillTextField.getText());
				provide.setRemark(remarkTextArea.getText());
				dao.updateProvide(provide);
				repaint();
				JOptionPane.showMessageDialog(getContentPane(), "数据修改成功！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);

			}
		});
		uodatetButton.setBounds(185, 388, 93, 23);
		contentPane.add(uodatetButton);

		closeButton = new JButton("退出");
		closeButton.setBackground(new Color(210, 105, 30));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_closeButton_actionPerformed(e);
			}
		});
		closeButton.setBounds(342, 388, 93, 23);
		contentPane.add(closeButton);
	}

	protected void do_closeButton_actionPerformed(ActionEvent arg0) {
		this.setVisible(false);
	}
}
