package politique;

public class R_Republiquains extends Regime{
	
	public R_Republiquains() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(3, "les ","Republiquains", 15, 1, 0.10, 60, new PT_Lents(), 0.7, false);
	}
}