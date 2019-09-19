package cases;

import java.awt.Color;

public class Mer extends Relief{
	
	private static Color couleur = new Color(50,160,240);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0.25;
	private static int id = 3;

	//builder
	public Mer() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
