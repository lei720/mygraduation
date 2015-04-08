package edu.cuit.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
import javax.swing.JTable;

import edu.cuit.bean.Depot;
import edu.cuit.bean.Dept;
import edu.cuit.bean.JoinDepot;
import edu.cuit.bean.OutDepot;
import edu.cuit.bean.Stock;
import edu.cuit.dao.DeptDao;
import edu.cuit.dao.JoinDepotDao;
import edu.cuit.dao.OutDepotDao;
import edu.cuit.dao.StockDao;
import edu.cuit.model.DeptModel;
import edu.cuit.model.JoinDepotModel;
import edu.cuit.model.OutDepotModel;
import edu.cuit.model.StockModel;
import edu.cuit.subframe.InserJoinDepotFrame;
import edu.cuit.subframe.InserOutDepotFrame;
import edu.cuit.subframe.InsertDeptFrame;
import edu.cuit.subframe.UpdateDeptFrame;
import edu.cuit.subframe.UpdateJoinDepotFrame;
import edu.cuit.subframe.UpdateOutDepotFrame;
import edu.cuit.subframe.UpdateProvideFrame;

public class DeptPanel extends JPanel {
	private JTextField contentTextField;
	final DeptModel model = new DeptModel();
	private JTable table_1;
	private DeptDao dao = new DeptDao();
	JComboBox comboBox ;
	/**
	 * Create the panel.
	 */
	public DeptPanel() {
		this.setBorder(createTitledBorder(null, "部门管理",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(59, 59, 59)));
		setSize(631, 438);
		setLayout(null);
		this.setBackground(new Color(160, 82, 45));
		JLabel conditionLabel = new JLabel("查询条件：");
		conditionLabel.setBackground(new Color(210, 105, 30));
		conditionLabel.setBounds(103, 86, 60, 15);
		add(conditionLabel);		
		
		contentTextField = new JTextField();
		contentTextField.setBackground(new Color(210, 105, 30));
		contentTextField.setBounds(264, 83, 156, 25);
		add(contentTextField);
		contentTextField.setColumns(10);
		
		JButton findButton = new JButton("搜索");
		findButton.setBackground(new Color(210, 105, 30));
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setRowCount(0);			
				String joinDate = contentTextField.getText();
				String condition = comboBox.getSelectedItem().toString();
				if(joinDate.equals("")){
					JOptionPane.showMessageDialog(getParent(), "没有填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(condition.equals("部门名称")){					
					Dept dept = dao.selectDeptByName(joinDate);
					if(dept!=null){
					model.addRow(new Object[] {dept.getId(),dept.getdName(),dept.getPrincipal(),dept.getBewrite()});
					}
				}
				if(condition.equals("负责人")){
					Dept dept = dao.selectDeptByPrincipal(joinDate);
					if(dept!=null){
						model.addRow(new Object[] {dept.getId(),dept.getdName(),dept.getPrincipal(),dept.getBewrite()});
					}
				}
				
			}
		});
		findButton.setBounds(430, 82, 74, 23);
		add(findButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(210, 105, 30));
		scrollPane.setBounds(10, 129, 611, 199);
		add(scrollPane);		
		
		
		table_1 = new JTable(model);	
		table_1.setBackground(new Color(210, 105, 30));
		List list = dao.selectDept();
		for (int i = 0; i < list.size(); i++) {
			Dept dept = (Dept)list.get(i);
			model.addRow(new Object[] {dept.getId(),dept.getdName(),dept.getPrincipal(),dept.getBewrite()});
		}
		if (list.size()<11) {
			model.setRowCount(11);
		} else {

		}
		scrollPane.setViewportView(table_1);
		JButton insertButton = new JButton("添加");
		insertButton.setBackground(new Color(210, 105, 30));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertDeptFrame frame = new InsertDeptFrame();
				frame.setVisible(true);
			}
		});
		insertButton.setBounds(188, 349, 66, 23);
		add(insertButton);		
		JButton updateButton = new JButton("修改");
		updateButton.setBackground(new Color(210, 105, 30));
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();  				
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要修改的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					File file = new File("file.txt");
					try {
						String column = model.getValueAt(row, 0).toString();			
						file.createNewFile();
						FileOutputStream out = new FileOutputStream(file);
						out.write((Integer.parseInt(column)));
						out.close();					
						UpdateDeptFrame frame = new UpdateDeptFrame();
						frame.setVisible(true);
						repaint();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});			
		updateButton.setBounds(282, 349, 66, 23);
		add(updateButton);
		
		JButton deleteButton = new JButton("删除");
		deleteButton.setBackground(new Color(210, 105, 30));
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();			
				if (row < 0) {
					JOptionPane.showMessageDialog(getParent(), "没有选择要刪除的数据！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String column =	model.getValueAt(row, 0).toString();
					dao.deleteDept(Integer.parseInt(column));
					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					repaint();
				}
			}
		});
		deleteButton.setBounds(399, 349, 66, 23);
		add(deleteButton);
		String [] dName = {"部门名称","负责人"};
		comboBox = new JComboBox(dName);		
		comboBox.setBackground(new Color(210, 105, 30));
		comboBox.setBounds(173, 83, 81, 21);
		add(comboBox);
	}
	
}
