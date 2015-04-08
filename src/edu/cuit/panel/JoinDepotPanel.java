package edu.cuit.panel;

import static javax.swing.BorderFactory.createTitledBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javafx.scene.control.ComboBox;

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
import edu.cuit.bean.JoinDepot;
import edu.cuit.bean.Stock;
import edu.cuit.dao.JoinDepotDao;
import edu.cuit.dao.StockDao;
import edu.cuit.model.JoinDepotModel;
import edu.cuit.model.StockModel;
import edu.cuit.subframe.InserJoinDepotFrame;
import edu.cuit.subframe.UpdateJoinDepotFrame;
import edu.cuit.subframe.UpdateProvideFrame;

public class JoinDepotPanel extends JPanel {
	private JTextField dateTextField;
	final JoinDepotModel model = new JoinDepotModel();
	private JTable table_1;
	private JoinDepotDao dao = new JoinDepotDao();
	private JComboBox comboBox =new JComboBox();
	/**
	 * Create the panel.
	 */
	public JoinDepotPanel() {
		this.setBorder(createTitledBorder(null, "仓库入库",
                TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, new Font(
                        "sansserif", Font.BOLD, 12), new Color(210, 105, 30)));
		setSize(631, 413);
		setLayout(null);
		this.setBackground(new Color(160, 82, 45));
		JLabel conditionLabel = new JLabel("订单号：");
		conditionLabel.setBackground(new Color(210, 105, 30));
		conditionLabel.setBounds(34, 83, 80, 20);
	//	conditionLabel.setBackground(new Color(71,201,223));
		add(conditionLabel);		
		
		dateTextField = new JTextField();
		dateTextField.setForeground(new Color(210, 105, 30));
		dateTextField.setBounds(347, 83, 156, 25);
		dateTextField.setBackground(new Color(210, 105, 30));
		add(dateTextField);
		dateTextField.setColumns(10);
		comboBox.setBackground(new Color(71,201,223));
		JButton findButton = new JButton("搜索");
	    findButton.setBackground(new Color(210, 105, 30));
		findButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oid = comboBox.getSelectedItem().toString();
				String joinDate = dateTextField.getText();
				if(oid.equals("")&&(joinDate.equals(""))){
					JOptionPane.showMessageDialog(getParent(), "没有填写查询条件！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(!oid.equals("")&&(joinDate.equals(""))){
					List list = dao.selectDepotByOid(oid);
					model.setRowCount(0);				
					for (int i = 0; i < list.size(); i++) {
						JoinDepot depot = (JoinDepot)list.get(i);
						String dRemark = depot.getRemark();
						if(dRemark.length()>4){
							dRemark = dRemark.substring(0, 4)+"...";
						}
						model.addRow(new Object[] { depot.getId(), depot.getoId(),
								depot.getdId(),depot.getWareName(),depot.getJoinTime(),depot.getWeight(),dRemark});
					}
				}
				else if(oid.equals("")&&(!joinDate.equals(""))){
					List list = dao.selectJoinDepot(joinDate);
					model.setRowCount(0);				
					for (int i = 0; i < list.size(); i++) {
						JoinDepot depot = (JoinDepot)list.get(i);
						String dRemark = depot.getRemark();
						if(dRemark.length()>4){
							dRemark = dRemark.substring(0, 4)+"...";
						}
						model.addRow(new Object[] { depot.getId(), depot.getoId(),
								depot.getdId(),depot.getWareName(),depot.getJoinTime(),depot.getWeight(),dRemark});
					}
				}
				else if(!oid.equals("")&&(!joinDate.equals(""))){
					List list = dao.selectJoinDepotAndDate(oid,joinDate);
					model.setRowCount(0);				
					for (int i = 0; i < list.size(); i++) {
						JoinDepot depot = (JoinDepot)list.get(i);
						String dRemark = depot.getRemark();
						if(dRemark.length()>4){
							dRemark = dRemark.substring(0, 4)+"...";
						}
						model.addRow(new Object[] { depot.getId(), depot.getoId(),
								depot.getdId(),depot.getWareName(),depot.getJoinTime(),depot.getWeight(),dRemark});
					}
				}
			}
		});
		findButton.setBounds(513, 82, 93, 23);
		add(findButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);
		scrollPane.setForeground(new Color(210, 105, 30));
		scrollPane.setBounds(32, 118, 589, 165);
		scrollPane.setBackground(new Color(210, 105, 30));
		add(scrollPane);		
		
		
		table_1 = new JTable(model);
		
		table_1.setBackground(new Color(210, 105, 30));
		final JoinDepotDao dao = new JoinDepotDao();
		List list = dao.selectJoinDepot();
		for (int i = 0; i < list.size(); i++) {
			JoinDepot depot = (JoinDepot)list.get(i);
			String dRemark = depot.getRemark();
			if(dRemark.length()>4){
				dRemark = dRemark.substring(0, 4)+"...";
			}
			model.addRow(new Object[] { depot.getId(), depot.getoId(),
					depot.getdId(),depot.getWareName(),depot.getJoinTime(),depot.getWeight(),dRemark});
		}
		if (list.size()<=15) {
			model.setRowCount(15);
		} else {

		}
		//scrollPane.setBounds(table_1.getBounds());
		scrollPane.setViewportView(table_1);
		JButton insertButton = new JButton("添加");
		insertButton.setBackground(new Color(210, 105, 30));
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InserJoinDepotFrame insertJoin = new InserJoinDepotFrame();
				insertJoin.setVisible(true);
			}
		});
		insertButton.setBounds(167, 369, 66, 23);
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
						UpdateJoinDepotFrame update = new UpdateJoinDepotFrame();
						update.setVisible(true);
						repaint();
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}
			}
		});			
		updateButton.setBounds(282, 369, 66, 23);
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
					dao.deleteJoinDepot(Integer.parseInt(column));
					JOptionPane.showMessageDialog(getParent(), "数据删除成功！",
							"信息提示框", JOptionPane.INFORMATION_MESSAGE);
					repaint();

				}
			}
		});
		deleteButton.setBounds(380, 369, 66, 23);
		add(deleteButton);
		
		JLabel dateLabel = new JLabel("入库时间：");
		dateLabel.setBackground(new Color(210, 105, 30));
		dateLabel.setBounds(282, 86, 66, 15);
		add(dateLabel);
		List lists = dao.selectOidId();
		String [] orderId = new String[lists.size()+1];
		orderId[0] = "";
		for(int i = 0;i<lists.size();i++){
			orderId[i+1 ]=  (String)lists.get(i);
		}
		comboBox = new JComboBox(orderId);		
		comboBox.setForeground(new Color(210, 105, 30));
		comboBox.setBackground(new Color(210, 105, 30));
		comboBox.setBounds(77, 83, 195, 21);
		add(comboBox);
	}
}
