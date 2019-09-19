package politique;

public class R_Etat_Policier extends Regime{
	
	public R_Etat_Policier() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(5, "l'","Etat Policer", 4, 5, 0.45, 55, new PT_Aggressifs(), 0.20, true);
	}
}