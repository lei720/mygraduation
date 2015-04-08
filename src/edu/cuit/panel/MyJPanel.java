package edu.cuit.panel;

import java.awt.*;

import java.net.*;


import javax.swing.*;
public class MyJPanel extends JPanel {
	public MyJPanel() {
		setBackground(new Color(160, 82, 45));
	}

public void	paintComponent(Graphics g){
	try{ 
		URL url = getClass().getResource("/edu/cuit/buttonIcons/Koala.jpg");
		ImageIcon image = new ImageIcon(url);
		g.drawImage(image.getImage(), 0, 0, this); 
		}catch(Exception e){} 
	}
}
