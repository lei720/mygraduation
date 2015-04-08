package edu.cuit.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class myBackPanel extends JPanel {
 
	 public myBackPanel() {
		// TODO Auto-generated constructor stub
		 setBackground(new Color(160, 82, 45));
	}
	
	public void	paintComponent(Graphics g){
		try{ 
			URL url = getClass().getResource("/edu/cuit/frame/buttonIcons/super.jpg");
			ImageIcon image = new ImageIcon(url);
			g.drawImage(image.getImage(), 0, 0, this); 
			}catch(Exception e){} 
		}
	

}
