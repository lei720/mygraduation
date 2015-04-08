package edu.cuit.jbutton;

import java.awt.*;

import javax.swing.*;
import javax.swing.table.*;

/**
* @version 1.0 11/09/98
*/
public class ButtonRenderer extends JButton implements TableCellRenderer {

  public ButtonRenderer() {
    setOpaque(true);
  }
  
  public Component getTableCellRendererComponent(JTable table, Object value,
                   boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(new Color(160, 82, 45));
      setBackground(new Color(160, 82, 45));
    } else{
      setForeground(new Color(160, 82, 45));
      setBackground(new Color(160, 82, 45));
    }
    setText( (value ==null) ? "" : value.toString() );
    return this;
  }
}
