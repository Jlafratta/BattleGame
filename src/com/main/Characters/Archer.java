package com.main.Characters;

import com.main.Equipment.*;
import com.main.Items.Inventory;
import javafx.scene.shape.Arc;

/**
 *          [ Arquero ]
 *
 *  - > Stats inciciales:
 *
 *      STR: 32
 *      DEF: 20
 *      STA: 80
 *
 *  - > Equipment:
 *
 *      - Bow
 *      - Silver Armor
 *
 ***/

public class Archer extends Character {


    public Archer(String name){
        super(name);
        setStats(new Stats(Archer.class));
        setWeapon(new Bow());
        setArmor(new SilverArmor());
    }

    public Archer(){
        setStats(new Stats(Archer.class));
    }

    @Override
    public String mainInfo(){
        String msgFmt = "\n\t>>>> %s <<<<\n Class: Archer\n ID: %d\n Level: %d";
        return String.format(msgFmt, getName(), getId(), getLevelSystem().getLevel());
    }

    @Override
    public String toString(){
        String msg = "\t>>>> "+ getName()+" <<<<\n Class: Archer";
        return msg.concat(super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof Archer);
        }
        return false;
    }
}
