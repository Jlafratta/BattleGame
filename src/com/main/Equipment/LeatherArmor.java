package com.main.Equipment;

/**
 *                 [ Leather Armor ]
 *
 *  - > Tier 1
 *
 ***/

public class LeatherArmor extends Armor{

    private final int leatherResistance = 20;

    public LeatherArmor(){
        setDefMod();
    }

    public int getLeatherResistance() {
        return leatherResistance;
    }

    @Override
    public void setDefMod(){
        this.defMod = getLeatherResistance();
    }
    @Override
    public int calculateArmor(){

        int def = getBaseDef()+(int)getDefMod();

        return def;
    }

    @Override
    public String toString(){
        String base = super.toString();
        return base.concat(String.format(" Type: Leather\n Defense: %d\n%s", calculateArmor(), isDefUpgrade() ? " Additional defense +35\n":"\n"));
    }
}
