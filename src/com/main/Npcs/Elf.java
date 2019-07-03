package com.main.Npcs;

public class Elf extends Npc {

    private final int magicAttack = 150;
    private final int EXP = 150;
    private final int GOLD = 300;

    /** Constructor **/

    public Elf(int tier){
        super();
        setName("[Elf]");
        setTier(tier);
        setDmg(calculateDmgNpc()*tier);
        setNpcStats(Elf.class);
        setExp(EXP*tier);         // La EXP varia un 10% (Aplicado en el getExp de la clase padre)
        setGold(GOLD*tier);       // El GOLD varia un 10% (Aplicado en el getGold de la clase padre)
    }


    @Override
    public int calculateDmgNpc(){
        return (int)Math.floor(Math.random()*(magicAttack - ((magicAttack*0.9))+1)+(magicAttack*0.9))*getTier();       //Variacion del 10% en el daño
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
