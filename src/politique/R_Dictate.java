package politique;

public class R_Dictate extends Regime{
	
	public R_Dictate() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(4, "le ","Dictate", 6, 2, (double)(0.10 + Math.random()*0.10), 75, new PT_Normaux(), 0.20, true);
	}
}