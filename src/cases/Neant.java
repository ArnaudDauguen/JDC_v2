package cases;

import java.awt.Color;

public class Neant extends Relief{
	
	private static Color couleur = new Color(0,0,0);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 0;
	private static int id = -1;

	//builder
	public Neant() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
