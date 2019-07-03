package com.main.Items;

import com.main.Characters.Character;

/**
 *                [ Critical Gem ]
 *
 *  - > Item que se utiliza para la mejora de armas.
 *
 *  - > Aumenta la chance de daño critico +15%
 *
 ***/

public class CriticalGem extends Item{

    private final float criticalUpgrade = 0.2f;

    public CriticalGem(){
        super();
    }

    public float getCriticalUpgrade() {
        return criticalUpgrade;
    }

    @Override
    public void applyItem(Character character) throws Exception{
        if (!character.getWeapon().isCriticalUpgrade()){
            character.getWeapon().setCriticalRate(Math.random()<getCriticalUpgrade());
            character.getWeapon().setCriticalUpgrade(true);
        }else {
            System.out.println("\n|=========================================|");
            throw new Exception("\n   Your weapon already has this upgrade");
        }
    }

    public String getName(){
        return " [ Critical Gem ] ";
    }

    @Override
    public String toString(){
        String msgFmt = " > Critical Gem \n\n   Increases critical \n    damage rate %% %.0f ";
        return String.format(msgFmt, ((getCriticalUpgrade()-0.05f)*100) );
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof CriticalGem);
        }
        return false;
    }


}
