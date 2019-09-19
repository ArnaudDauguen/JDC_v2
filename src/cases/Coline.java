package cases;

import java.awt.Color;

public class Coline extends Relief{
	
	private static Color couleur = new Color(0,0,0);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1.5;
	private static int id = 9;

	//builder
	public Coline() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
