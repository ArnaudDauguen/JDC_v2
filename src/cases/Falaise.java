package cases;

import java.awt.Color;

public class Falaise extends Relief{
	
	private static Color couleur = new Color(145,145,145);
	private static boolean feetCrossable = true;
	private static double bonusArmee = 5;
	private static int id = 7;

	//builder
	public Falaise() {
		super(couleur, feetCrossable, bonusArmee, id);
		
	}
	
}
