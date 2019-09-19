package politique;

public class R_Democratie extends Regime{
	
	public R_Democratie() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(2, "la ","Démocratie", 20, 1, 0.25, 90, new PT_Lents(), 0.75, false);
	}
}