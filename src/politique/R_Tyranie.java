package politique;

public class R_Tyranie extends Regime{
	
	public R_Tyranie() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(8, "la ","Tyranie", 2, 1, 0.20, 35, new PT_Rapides(), 0.8, true);
	}
}