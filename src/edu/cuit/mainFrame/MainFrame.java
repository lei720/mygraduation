package edu.cuit.mainFrame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class MainFrame extends JFrame {
	private JPanel ConPane;
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 948, 700);
		ConPane = new JPanel();
		ConPane.setBackground(new Color(210, 105, 30));
		ConPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(ConPane);
		ConPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(210, 105, 30));
		panel.setBounds(0, 0, 932, 662);
		ConPane.add(panel);
	}
}
