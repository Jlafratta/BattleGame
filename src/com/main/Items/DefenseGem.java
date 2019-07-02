package com.main.Items;

import com.main.Characters.Character;

/**
 *                [ Defense Gem ]
 *
 *  - > Item que se utiliza para la mejora de armaduras.
 *
 *  - > Aumenta la defensa de la armadura +35
 *
 *
 ***/

public class DefenseGem extends Item {

    private final int defenseUpgrade = 35;

    public DefenseGem(){
        super();
    }

    public int getDefenseUpgrade() {
        return defenseUpgrade;
    }

    @Override
    public void applyItem(Character character) throws Exception{
        if (!character.getArmor().isDefUpgrade()){
            character.getArmor().setBaseDef(character.getArmor().getBaseDef()+getDefenseUpgrade());
            character.getArmor().setDefUpgrade(true);
        }else {
            System.out.println("\n|=========================================|");
            throw new Exception("\n   Your armor already has this upgrade");
        }
    }

    public String getName(){
        return " [ Defense Gem ] ";
    }

    @Override
    public String toString(){
        String msgFmt = " > Defense Gem \n\n   Increases armor \n    defense +%d ";
        return String.format(msgFmt, getDefenseUpgrade());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof DefenseGem);
        }
        return false;
    }
}
