package cases;

import java.awt.Color;

public class Lac extends Relief{
	
	private static Color couleur = new Color(45,220,255);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0.25;
	private static int id = 5;

	//builder
	public Lac() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
