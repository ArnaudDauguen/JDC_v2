package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import Civilisations.Civilisation;
import cases.Case;
 
public class PanelCarte extends JPanel {
	
	private ArrayList<Civilisation> civilisations = new ArrayList<>();
	private int[][] tempCarte;
	private int[][] tempBiome;
	private int tailleX;
	private int tailleY;
	private Case[][] trueMap;
	private boolean haveTrueMap = false;
	
	public void paintComponent(Graphics g){
		
		if(haveTrueMap == false) {
			paintTempRelief(g);
			paintTempBiome(g);
		}else {
			paintTrueMap(g);
		}
		
		paintCiv(g);
		
	}
	
	//fonctions de peinture
	public void paintTempRelief(Graphics g) {
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				g.setColor(new Color(tempCarte[x][y]*2));
				g.fillRect(16*x +1, 16*y +1, 15, 15);
			}
		}
	}
	public void paintTempBiome(Graphics g) {
		//peinture biome
				//1.ocean_profond:<niveau/3 ; 2.ocean:<niveau-5 ; 3.Litoral:<niveau ; 4.lac:niveau-10 ; 5.plage:niveau+1 ; 6.falaise:niveau+2 ;
				//10.plaines:niveau ; 11.coline:niveau+15 -> niveau+20 ; 12.montagne:>niveau+45 ; 13.volcan:127
				//21.forets ; 22.jungle ; 23.marais ; 24.desert ; 25.prairies
					for(int x = 0; x < tailleX; x++) {
						for(int y = 0; y < tailleY; y++) {
							//choix de la couleur en fct du biome
							switch (tempBiome[x][y]) {
							case 1:
								g.setColor(new Color(0,0,175));
								break;
							case 2:
								g.setColor(new Color(75,75,255));
								break;
							case 3:
								g.setColor(new Color(75,175,255));
								break;
							case 4:
								g.setColor(new Color(45,220,255));
								break;
							case 5:
								g.setColor(new Color(255,255,170));
								break;
							case 6:
								g.setColor(new Color(145,145,145));
								break;
							case 12:
								g.setColor(new Color(180,130,111));
								break;
							case 13:
								g.setColor(new Color(230,60,50));
								break;
							case 21:
								g.setColor(new Color(110,190,10));
								break;
							case 22:
								g.setColor(new Color(125,165,40));
								break;
							case 23:
								g.setColor(new Color(140,200,125));
								break;
							case 24:
								g.setColor(new Color(250,250,50));
								break;
							case 25:
								g.setColor(new Color(50,230,75));
								break;
							default:
								g.setColor(new Color(0,0,0));
								break;
							}
						
						g.fillRect(16*x +1, 16*y +1, 15, 15);
						}
					}
	}
	public void paintTrueMap(Graphics g) {
		for(int x = 0; x < tailleX; x++) {
			for(int y = 0; y < tailleY; y++) {
				g.setColor(trueMap[x][y].getColor());
				g.fillRect(16*x +1, 16*y +1, 15, 15);
			}
		}
	}
	public void paintCiv(Graphics g) {
		//pour chaque civ
		for(Civilisation civ : civilisations) {
			try {
				if(civ != null) {
					if(civ.alive()) {
						g.setColor(civ.getColor());
						//chaque case
						for(Case c : civ.getCase()) {
							g.fillRect(16*c.getX() +1, 16*c.getY() +1, 15, 15);
						}
					}
				}
			}catch(Exception e) {}
		}
	}
	
	
	
	
	//fct recup des maps
	public void setReliefCarte(int[][] tempCarte, int tailleX, int tailleY) {
		this.tempCarte = tempCarte;
		this.tailleX = tailleX;
		this.tailleY = tailleY;
	}
	public void setBiomeCarte(int[][] tempBiome, int tailleX, int tailleY) {
		this.tempBiome = tempBiome;
		this.tailleX = tailleX;
		this.tailleY = tailleY;
	}
	public void setCiv(ArrayList<Civilisation> civs) {
		this.civilisations = civs;
	}
	public void setTrueMap(Case[][] map, int tailleX, int tailleY) {
		this.tailleX = tailleX;
		this.tailleY = tailleY;
		haveTrueMap = true;
		trueMap = map;
	}

}