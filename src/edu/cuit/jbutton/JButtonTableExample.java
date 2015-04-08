package edu.cuit.jbutton;
import javax.swing.*;
import javax.swing.table.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import edu.cuit.bean.Stock;
import edu.cuit.dao.StockDao;
import java.awt.Color;
public class JButtonTableExample extends JPanel {
	  public JButtonTableExample(){
	  	setBackground(new Color(160, 82, 45));	    
	    DefaultTableModel dm = new DefaultTableModel();
	
	    dm.setDataVector(new Object[][]{{"","","","","","","",""}},
                     new Object[]{"是否入库","编号","货品名称","订单号","交货日期","进货商","金额","数量"});	                     
	    dm.removeRow(0);
	    dm.setRowCount(20);
	    JTable table = new JTable(dm);
	    table.setBackground(new Color(210, 105, 30));
	    StockDao dao = new StockDao();
	    List list = dao.selectStock();
	    for(int i = 0;i<list.size();i++){
	    	Stock stock = (Stock)list.get(i);
	    	String oid = stock.getOrderId();
	    	int id = dao.selectJoinStockByOid(oid);	    	
	    	if(id <=0){
	    		 dm.addRow(new Object[]{"入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
	    				 stock.getMoney(),stock.getCount()});
	    		  File file = new File("files.txt");	    		  
	    		 try {	    				
	    				FileOutputStream out = new FileOutputStream(file);
	    				out.write(stock.getId());
	    				out.close();	    			
	    				repaint();			    				
	    			} catch (Exception ee) {
	    				ee.printStackTrace();
	    			}
	    	}
	    	else{
	    		 dm.addRow(new Object[]{"已经入库",stock.getId(),stock.getsName(),stock.getOrderId(),stock.getConsignmentDate(),stock.getBaleName(),
	    				 stock.getMoney(),stock.getCount()});
	    	}
	    }	   
	    
	    JScrollPane scroll = new JScrollPane(table);	
	    scroll.setBackground(new Color(160, 82, 45));
	    table.getColumn("是否入库").setCellRenderer(new ButtonRenderer());
	    table.getColumn("是否入库").setCellEditor(new ButtonEditor(new JCheckBox()));
	    this.add(scroll);
	  //  this.setSize( 583, 230);
	    int row = table.getSelectedRow();  			   
		if (row < 0) {			
			return;
		} else {
			File file = new File("filedd.txt");
			
			try {
				String column = dm.getValueAt(row, 0).toString();	
				System.out.println("COLUMN："+column);
				file.createNewFile();
				FileOutputStream out = new FileOutputStream(file);
				out.write((Integer.parseInt(column)));
				out.close();					
				repaint();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
	    setVisible(true);
	  
	  }
	}
