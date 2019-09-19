package cases;

import java.awt.Color;

public class Litoral extends Relief{
	
	private static Color couleur = new Color(75,175,255);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 1;
	private static int id = 4;

	//builder
	public Litoral() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
