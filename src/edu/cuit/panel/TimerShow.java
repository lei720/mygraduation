package edu.cuit.panel;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerShow extends JPanel implements Runnable {

    /*public static void main(String[] args) {
        
    }
*/
    private JLabel lblNewLabel;

    public TimerShow() {
        super();
        setBounds(23, 366, 178, 35);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //   getContentPane().setLayout(null);
       lblNewLabel = new JLabel(" ºÚµ• ±÷” ", JLabel.CENTER);
     //   lblNewLabel.setBounds(0, (300-35-35)/2, 440, 35);
      //  getContentPane().add(lblNewLabel);
        setVisible(true);
        add(lblNewLabel);
        new Timer(delay, taskPerformer).start();
    }
    
    int delay = 1000; //milliseconds
    ActionListener taskPerformer = new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
            String s=String.format("%tY-%<tm-%<td   %<tH:%<tM:%<tS",new Date());
            lblNewLabel.setText(s);
        }
    };

	@Override
	public void run() {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TimerShow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}    
}