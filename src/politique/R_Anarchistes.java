package politique;

public class R_Anarchistes extends Regime{
	
	public R_Anarchistes() {
		//super(id, préfixe, nom, baseBonheur, baseEfficaciteArmee, tauxArmeeAttendu, stabilite, polTerrClassic, soutienDeGuerre, canDeclareWar);
		super(0, "les ","Anarchistes", 0, 1, (double)(0.05 + Math.random()*0.1), 0, new PT_Necessitaires(), Math.random(), false);
	}
}