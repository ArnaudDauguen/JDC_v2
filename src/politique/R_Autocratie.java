package politique;

public class R_Autocratie extends Regime{
	
	public R_Autocratie() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(1, "l' ","Autocratie", 10, 3, 0.20, 85, new PT_Rapides(), 0.20, true);
	}
}