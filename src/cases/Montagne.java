package cases;

import java.awt.Color;

public class Montagne extends Relief{
	
	private static Color couleur = new Color(180,130,111);
	private static boolean feetCrossable = false;
	private static double bonusArmee = 3;
	private static int id = 10;

	//builder
	public Montagne() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
	//fct Parente
	public Color getColor() {return couleur;}

}
