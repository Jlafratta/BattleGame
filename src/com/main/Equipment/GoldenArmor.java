package com.main.Equipment;

/**
 *                 [ Golden Armor ]
 *
 *  - > Tier 3
 *
 ***/

public class GoldenArmor extends Armor {

    private final int goldenResistance = 70;

    public GoldenArmor(){
        setDefMod();
    }

    public int getGoldenResistance() {
        return goldenResistance;
    }

    @Override
    public void setDefMod(){
        this.defMod = getGoldenResistance();
    }
    @Override
    public int calculateArmor(){

        int def = getBaseDef()+(int)getDefMod();

        return def;
    }

    @Override
    public String toString(){
        String base = super.toString();
        return base.concat(String.format(" Type: Golden\n Defense: %d\n%s", calculateArmor(), isDefUpgrade() ? " Additional defense +35\n":"\n"));
    }
}