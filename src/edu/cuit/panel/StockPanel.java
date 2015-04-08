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









import edu.cuit.bean.Stock;
import edu.cuit.dao.StockDao;
import edu.cuit.jbutton.JButtonTableExample;
import edu.cuit.model.StockModel;
import edu.cuit.subframe.InserStockFrame;
import edu.cuit.subframe.UpdateJoinDepotFrame;
import edu.cuit.subframe.UpdateStockFrame;

import java.io.*;
public class StockPanel extends JPanel {
	
	private JTable table;
	
	/**
	 * Create the panel.
	 */
	public StockPanel() {
		
		this.setBackground(new Color(160, 82, 45));
		setSize(631, 420);
		setLayout(null);
		//String[] name = {"客户","订单号","交货日期"};
/*		StockDao dao = new StockDao();
		List list = dao.selectStock();
		Object obj[][] = new Object[list.size()][list.size()];		
		for(int i = 0;i<list.size();i++){			
			Stock stock = (Stock)list.get(i);
			 obj[0][i] =stock.getBaleName();;
		  };	*/
		JButtonTablePanel panel = new JButtonTablePanel();
		panel.setBackground(new Color(160, 82, 45));
	//	panel.setBackground(new Color(160, 82, 45));
		panel.setBounds(0, 0, 631, 420);
		add(panel);
	}
}
