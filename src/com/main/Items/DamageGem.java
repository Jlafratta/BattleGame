package com.main.Items;

import com.main.Characters.Character;

/**
 *                [ Damage Gem ]
 *
 *  - > Item que se utiliza para la mejora de armas.
 *
 *  - > Aumenta el daño base del arma +28
 *
 *
 ***/

public class DamageGem extends Item {

    private final int damageUpgrade = 28;

    public DamageGem(){
        super();
    }

    public int getDamageUpgrade() {
        return damageUpgrade;
    }

    @Override
    public void applyItem(Character character) throws Exception{
        if (!character.getWeapon().isDmgUpgrade()){
            character.getWeapon().setBaseDmg(character.getWeapon().getBaseDmg()+getDamageUpgrade());
            character.getWeapon().setDmgUpgrade(true);
        }else {
            System.out.println("\n|=========================================|");
            throw new Exception("\n   Your weapon already has this upgrade");
        }
    }

    public String getName(){
        return " [ Damage Gem ] ";
    }

    @Override
    public String toString(){
        String msgFmt = " > Damage Gem \n\n   Increases weapon \n    damage +%d ";
        return String.format(msgFmt, getDamageUpgrade());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof DamageGem);
        }
        return false;
    }
}
