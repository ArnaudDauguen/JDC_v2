package cases;

import java.awt.Color;

public class Ocean_de_Glace extends Relief{
	
	private static Color couleur = new Color(190,250,250);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0.1;
	private static int id = 2;

	//builder
	public Ocean_de_Glace() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
