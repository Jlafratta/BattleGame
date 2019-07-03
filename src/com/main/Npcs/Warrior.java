package com.main.Npcs;

public class Warrior extends Npc {

    private final int meleeAttack = 120;
    private final int EXP = 250;
    private final int GOLD = 100;

    /** Constructor **/

    public Warrior(int tier){
        super();
        setName("[Warrior]");
        setTier(tier);
        setDmg(calculateDmgNpc()*tier);
        setNpcStats(Warrior.class);
        setExp(EXP*tier);           // La EXP varia un 10% (Aplicado en el getExp de la clase padre)
        setGold(GOLD*tier);         // El GOLD varia un 10% (Aplicado en el getGold de la clase padre)
    }


    @Override
    public int calculateDmgNpc(){
        return (int)Math.floor(Math.random()*(meleeAttack - ((meleeAttack *0.9))+1)+(meleeAttack *0.9))*getTier();       //Variacion del 10% en el daño
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
