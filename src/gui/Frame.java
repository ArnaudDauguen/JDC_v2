package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Civilisations.Civilisation;
import cases.Case;

public class Frame extends JFrame {
	
	private PanelCarte panelCarte;
	public Frame() {
		//param fenetre
		this.setTitle("JDC v2");
		//this.setSize(1350, 720);//1280*720
		//this.setSize(1510, 900);//1440*900
		this.setSize(1950,1050); //1920*1080
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//param panel Carte
		panelCarte = new PanelCarte();
		
		panelCarte.setBackground(Color.ORANGE);
		this.setContentPane(panelCarte);
		this.setVisible(true);
	}
	
	
	public void setReliefMonde(int[][] carte, int x, int y) {panelCarte.setReliefCarte(carte, x , y);}
	public void setBiomeMonde(int[][] carte, int x, int y) {panelCarte.setBiomeCarte(carte, x , y);}
	public void setCiv(ArrayList<Civilisation> civs) {panelCarte.setCiv(civs);}
	public void setTrueMap(Case[][] map, int x, int y) {panelCarte.setTrueMap(map, x, y);}
	public void repaint() {panelCarte.repaint();}
	
}
