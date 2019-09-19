package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel extends JPanel implements ActionListener{

	

	public Panel() {
		
	}
	
	
	/*
	
	public void paintComponent(Graphics g){
		g.setColor(new Color(100, 100, 100));
		//carte
		for(int x = 0 ; x <= 29 ; x++){
			for(int y = 0 ; y <= 49 ; y++){
				
				g.setColor(tempMonde[x][y],tempMonde[x][y],tempMonde[x][y]);
				g.fillRect(y * 10, x * 10, 10, 10);
				g.setColor(Color.black);
				g.drawRect(y * 10, x * 10, 10, 10);
			}
		}
	}
	
		*/
		
		
		
		
		
	//action à faire
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	

}
