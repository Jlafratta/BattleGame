package com.main.Equipment;

/**
 *                 [ Silver Armor ]
 *
 *  - > Tier 2
 *
 ***/

public class SilverArmor extends Armor {

    private final int silverResistance = 45;

    public SilverArmor(){
        setDefMod();
    }

    public int getSilverResistance() {
        return silverResistance;
    }

    @Override
    public void setDefMod(){
        this.defMod = getSilverResistance();
    }
    @Override
    public int calculateArmor(){

        int def = getBaseDef()+(int)getDefMod();

        return def;
    }

    @Override
    public String toString(){
        String base = super.toString();
        return base.concat(String.format(" Type: Silver\n Defense: %d\n%s", calculateArmor(), isDefUpgrade() ? " Additional defense +35\n":"\n"));
    }
}
