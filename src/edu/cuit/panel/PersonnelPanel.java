package edu.cuit.panel;
import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JScrollPane;

import edu.cuit.bean.BasicMessage;
import edu.cuit.bean.Contact;
import edu.cuit.bean.Dept;
import edu.cuit.dao.DeptDao;
import edu.cuit.dao.PersonnelDao;
import edu.cuit.model.JoinDepotModel;
import edu.cuit.subframe.InsertPersonnelFrame;
import edu.cuit.subframe.UpdatePersonnelFrame;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JRadioButton;
public class PersonnelPanel extends JPanel {
	final JoinDepotModel model = new JoinDepotModel();
	private final PersonnelDao perdao = new PersonnelDao();
	private JTextField nameTextField;
	private JTextField ageTextField;
	private JComboBox pNameComboBox = new JComboBox();
	JPanel contactpanel;
	private Contact contact = new Contact();
	JPanel panel_1 = new JPanel();	
	JPanel contentPanel = new JPanel();
	private BasicMessage message = new BasicMessage();
	private JRadioButton manRadioButton;
	private JRadioButton wradioButton ;
	private JTextField deptField;
	private JTextField headshipField ;
	private JTextField pnoneTextField;
	private JTextField officeTextField;
	private JTextField faxTextField;
	private JTextField emailtextField;
	private JTextField addressTextField;
	private int pId;
	public PersonnelPanel() {
		this.setBorder(createTitledBorder(null, "人员管理",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		setSize(631, 427);
		setLayout(null);
		this.setBackground(new Color(160, 82, 45));
		panel_1.setBackground(new Color(160, 82, 45));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(160, 82, 45));
		panel.setBorder(BorderFactory.createTitledBorder(null, "员工信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
				new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		panel.setBounds(29, 25, 572, 71);
		add(panel);
		panel.setLayout(null);
		JLabel dNameLabel = new JLabel("部  门：");
		dNameLabel.setBackground(new Color(210, 105, 30));
		dNameLabel.setBounds(70, 35, 54, 15);
		panel.add(dNameLabel);
		final DeptDao dao = new DeptDao();
List list = dao.selectDept();					//调用查询所有部门信息方法
String dName[] = new String[list.size() + 1];	//根据查询结果创建字符串数组对象
dName[0] = "";
for (int i = 0; i < list.size(); i++) {			//循环遍历查询结果集
	Dept dept = (Dept) list.get(i);
	dName[i + 1] = dept.getdName();				//获取查询结果中部门名称
}		
final JComboBox dNamecomboBox = new JComboBox(dName);	//实例化下拉列表对象
dNamecomboBox.setBackground(new Color(210, 105, 30));
	dNamecomboBox.addActionListener((new ActionListener() {		//添加下拉列表监听事件
			@Override
			public void actionPerformed(ActionEvent e) {			
				pNameComboBox.removeAllItems();
				String dName = dNamecomboBox.getSelectedItem().toString();	//获取用户选择的部门名称
				DeptDao deptDao = new DeptDao();				//定义保存有操作数据库类对象
				int id = deptDao.selectDeptIdByName(dName);		//调用获取部门编号方法
				List<String> listName = perdao.selectBasicMessageByDept(id);	//调用按部门编号查询所有员工信息方法				
				for (int i = 0; i < listName.size(); i++) {		//循环遍历查询结果集
					pNameComboBox.addItem(listName.get(i));		//像姓名下拉列表中添加元素				
				}
				repaint();
		}
}));

		dNamecomboBox.setBounds(134, 32, 102, 21);
		panel.add(dNamecomboBox);

		JLabel pNameLabel = new JLabel("姓名：");
		pNameLabel.setBackground(new Color(210, 105, 30));
		pNameLabel.setBounds(262, 35, 54, 15);
		panel.add(pNameLabel);	
		pNameComboBox.setBackground(new Color(210, 105, 30));

		
		pNameComboBox.setBounds(310, 32, 101, 21);
		panel.add(pNameComboBox);
		
		contentPanel.setLayout(null);
		
		panel_1.setBorder(BorderFactory.createTitledBorder(null, "员工信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP,
				new Font("sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		panel_1.setBounds(29, 106, 572, 310);
		add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(210, 105, 30));
		scrollPane.setBorder(createTitledBorder(null, "员工信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		scrollPane.setBounds(21, 53, 109, 218);
		panel_1.add(scrollPane);
		String name[] = { "基本信息", "联系方式" };
		final JList jlist = new JList(name);
		jlist.setBackground(new Color(160, 82, 45));
		final JPanel bpanel =  basicPanel();
		final JPanel particular = particularPsnel();
jlist.addListSelectionListener(new ListSelectionListener() {
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			if(dNamecomboBox.getSelectedItem() == null ||pNameComboBox.getSelectedItem()== null){
				JOptionPane.showMessageDialog(getParent(), "没有选择查询的员工！",
						"信息提示框", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			JList list = (JList) e.getSource();// 获取事件源
			// 获取列表选项并转换为字符串
			String value = (String) list.getSelectedValue();
			if(value.equals("基本信息")){									//判断用户是否选择了“基本信息”
				String name = pNameComboBox.getSelectedItem().toString();	//获取用户选择的员工姓名
				panel_1.remove(particular);									//移除显示员工详细信息的面板	
				panel_1.add(bpanel);					
				bpanel.setBounds(140, 53, 409, 208);
				message = perdao.selectBNameById(dNamecomboBox.getSelectedItem().toString(),name);			//调用查询数据方法
				pId = message.getId();										
				nameTextField.setText(message.getName());					//设置员工基本系统中的组件内容
				ageTextField.setText(message.getAge()+"");
				String sex = message.getSex();										
				if(sex.equals("男")){
					manRadioButton.setSelected(true);						//设置窗体中性别单选按钮的显示内容
				}
				else{
					wradioButton.setSelected(true);
				}
				int dept = message.getDept();
				Dept depts = dao.selectDepotById(dept);						//按照部门编号查询员工所在部门信息
				deptField.setText(depts.getdName());						//设置员工部门内容
			    String hName =	perdao.selectHeadshipById(message.getHeadship());
			    headshipField.setText(hName);
			    repaint();
			}
			if(value.equals("联系方式")){
				panel_1.remove(bpanel);
				panel_1.add(particular);					
				contact = perdao.selectContactById(pId);
				pnoneTextField.setText(contact.getContact());
				officeTextField.setText(contact.getOfficePhone());
				faxTextField.setText(contact.getFax());
				emailtextField.setText(contact.getEmail());
				addressTextField.setText(contact.getFaddress());
				repaint();
			}
			doLayout();
		}

	}
});
		scrollPane.setViewportView(jlist);
		JButton insertButton = new JButton("添加");
		insertButton.setBackground(new Color(210, 105, 30));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertPersonnelFrame frame = new InsertPersonnelFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(179, 266, 62, 23);
		panel_1.add(insertButton);

		JButton updateButton = new JButton("修改");
		updateButton.setBackground(new Color(210, 105, 30));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				if(dNamecomboBox.getSelectedItem() == null || pNameComboBox.getSelectedItem()== null){
					JOptionPane.showMessageDialog(getParent(), "请输入修改条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				message = perdao.selectBNameById(dNamecomboBox.getSelectedItem().toString(),pNameComboBox.getSelectedItem().toString());
				pId = message.getId();
				if (pId < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {						 
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);					
						out.write(pId);
						out.close();									
						UpdatePersonnelFrame updateFrame = new UpdatePersonnelFrame();
						updateFrame.setVisible(true);
						repaint();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});
		updateButton.setBounds(275, 266, 62, 23);
		panel_1.add(updateButton);

JButton deleteButton = new JButton("删除");
deleteButton.setBackground(new Color(210, 105, 30));
deleteButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		   int n = JOptionPane.showConfirmDialog(getParent(), 
                    "确认正确吗？","确认对话框",	JOptionPane.YES_NO_CANCEL_OPTION); 
		   message = perdao.selectBNameById(dNamecomboBox.getSelectedItem().toString(),pNameComboBox.getSelectedItem().toString());
			pId = message.getId();
		   if(n == JOptionPane.YES_OPTION){ 			//如果用户确认信息
			   perdao.deleteBasicMessage(pId);			//调用删除数据方法
			   pNameComboBox.removeAllItems();
			   repaint();
		   }
	}
});
		deleteButton.setBounds(362, 266, 62, 23);
		panel_1.add(deleteButton);		
			

	}
	public JPanel particularPsnel(){
		contactpanel = new JPanel();
		contactpanel.setBackground(new Color(210, 105, 30));
		contactpanel.setBounds(128, 53, 420, 208);		
		contactpanel.setLayout(null);
		
		JLabel phoneLabel = new JLabel("手机：");
		phoneLabel.setBackground(new Color(210, 105, 30));
		phoneLabel.setBounds(25, 35, 54, 15);
		contactpanel.add(phoneLabel);
		
		pnoneTextField = new JTextField();
		pnoneTextField.setBackground(new Color(210, 105, 30));
		pnoneTextField.setBounds(60, 32, 133, 25);
		contactpanel.add(pnoneTextField);
		pnoneTextField.setColumns(10);
		
		JLabel officeLabel = new JLabel("办公电话：");
		officeLabel.setBackground(new Color(210, 105, 30));
		officeLabel.setBounds(204, 35, 69, 15);
		contactpanel.add(officeLabel);
		
		officeTextField = new JTextField();
		officeTextField.setBackground(new Color(210, 105, 30));
		officeTextField.setBounds(264, 32, 121, 25);
		contactpanel.add(officeTextField);
		officeTextField.setColumns(10);
		
		JLabel faxLabel = new JLabel("传真：");
		faxLabel.setBackground(new Color(210, 105, 30));
		faxLabel.setBounds(25, 82, 36, 15);
		contactpanel.add(faxLabel);
		
		faxTextField = new JTextField();
		faxTextField.setBackground(new Color(210, 105, 30));
		faxTextField.setBounds(61, 82, 133, 25);
		contactpanel.add(faxTextField);
		faxTextField.setColumns(10);
		
		JLabel emailLabel = new JLabel("邮箱：");
		emailLabel.setBackground(new Color(210, 105, 30));
		emailLabel.setBounds(226, 82, 36, 25);
		contactpanel.add(emailLabel);
		
		emailtextField = new JTextField();
		emailtextField.setBackground(new Color(210, 105, 30));
		emailtextField.setColumns(10);
		emailtextField.setBounds(264, 82, 121, 25);
		contactpanel.add(emailtextField);
		
		JLabel addressLabel = new JLabel("家庭住址：");
		addressLabel.setBackground(new Color(210, 105, 30));
		addressLabel.setBounds(10, 145, 60, 15);
		contactpanel.add(addressLabel);
		
		addressTextField = new JTextField();
		addressTextField.setBackground(new Color(210, 105, 30));
		addressTextField.setColumns(10);
		addressTextField.setBounds(61, 140, 133, 25);
		contactpanel.add(addressTextField);
		contactpanel.setBorder(createTitledBorder(null, "详细信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		return contactpanel;
	}
	public JPanel basicPanel(){		
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(210, 105, 30));
		JLabel nameLabel = new JLabel("姓名：");
		nameLabel.setBackground(new Color(210, 105, 30));
		nameLabel.setBounds(25, 35, 54, 15);
		contentPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBackground(new Color(210, 105, 30));
		nameTextField.setBounds(64, 32, 123, 25);
		contentPanel.add(nameTextField);
		nameTextField.setColumns(10);

		JLabel agelabel = new JLabel("年龄：");
		agelabel.setBackground(new Color(210, 105, 30));
		agelabel.setBounds(208, 35, 41, 15);
		contentPanel.add(agelabel);

		ageTextField = new JTextField();
		ageTextField.setBackground(new Color(210, 105, 30));
		ageTextField.setColumns(10);
		ageTextField.setBounds(250, 30, 123, 25);
		contentPanel.add(ageTextField);

		JLabel sexLabel = new JLabel("性别：");
		sexLabel.setBackground(new Color(210, 105, 30));
		sexLabel.setBounds(25, 82, 41, 15);
		contentPanel.add(sexLabel);

		manRadioButton = new JRadioButton("男");
		manRadioButton.setBackground(new Color(210, 105, 30));
		manRadioButton.setBounds(66, 78, 41, 23);
		contentPanel.add(manRadioButton);

		wradioButton = new JRadioButton("女");
		wradioButton.setBackground(new Color(210, 105, 30));
		wradioButton.setBounds(119, 78, 41, 23);
		contentPanel.add(wradioButton);
		ButtonGroup group = new ButtonGroup();
		group.add(manRadioButton);
		group.add(wradioButton);
		JLabel deptLabel = new JLabel("部门：");
		deptLabel.setBackground(new Color(210, 105, 30));
		deptLabel.setBounds(208, 82, 41, 15);
		contentPanel.add(deptLabel);

		deptField = new JTextField();
		deptField.setBackground(new Color(210, 105, 30));
		deptField.setBounds(250, 79, 123, 25);
		contentPanel.add(deptField);

		JLabel headshipLabel = new JLabel("职务：");
		headshipLabel.setBackground(new Color(210, 105, 30));
		headshipLabel.setBounds(25, 123, 41, 15);
		contentPanel.add(headshipLabel);

		headshipField  = new JTextField();
		headshipField.setBackground(new Color(210, 105, 30));
		headshipField.setBounds(64, 120, 123, 25);
		contentPanel.add(headshipField);
		contentPanel.setBorder(createTitledBorder(null, "基本信息",
				TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
						"sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		return contentPanel;
	}	
}
