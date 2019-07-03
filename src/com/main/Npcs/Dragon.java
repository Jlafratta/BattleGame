package com.main.Npcs;

public class Dragon extends Npc{

    private final int fireAttack = 70;
    private final int EXP = 1000;
    private final int GOLD = 5000;

    /** Constructor **/

    public Dragon(int tier){
        super();
        setName("[Dragon]");
        setTier(tier);
        setDmg(calculateDmgNpc()*tier);
        setNpcStats(Dragon.class);
        setExp(EXP*tier);       // La EXP varia un 10% (Aplicado en el getExp de la clase padre)
        setGold(GOLD*tier);     // El GOLD varia un 10% (Aplicado en el getGold de la clase padre)
    }


    @Override
    public int calculateDmgNpc(){
        return (int)Math.floor(Math.random()*(fireAttack - ((fireAttack *0.9))+1)+(fireAttack *0.9))*getTier();//Variacion del 10% en el daño
    }

    @Override
    public boolean equals(Object obj){
        return super.equals(obj);
    }

    @Override
    public String toString(){
        String base = super.toString();
        return "\t-=> "+getName()+" <=-\n Tier "+getTier()+base;
    }
}
