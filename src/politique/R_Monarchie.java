package politique;

public class R_Monarchie extends Regime{
	
	public R_Monarchie() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(7, "la ","Monarchie", 12, 1.5, 0.15, 75, new PT_Normaux(), 0.5, false);
	}
}