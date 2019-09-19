package cases;

import java.awt.Color;

public class Plage extends Relief{
	
	private static Color couleur = new Color(255,255,170);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1;
	private static int id = 6;

	//builder
	public Plage() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
