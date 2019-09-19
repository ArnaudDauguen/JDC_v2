package cases;

import java.awt.Color;

public class Ocean extends Relief{
	
	private static Color couleur = new Color(75,75,255);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0;
	private static int id = 1;

	//builder
	public Ocean() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
