package politique;

public class R_Etat_Militaire extends Regime{
	
	public R_Etat_Militaire() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(6, "l'","Etat Militaire", 4, 6, 0.50, 60, new PT_Aggressifs(), 0.10, true);
	}
}