package com.main.Characters;

import com.main.Equipment.*;
import com.main.Items.Inventory;

/**
 *          [ Mago ]
 *
 *  - > Stats inciciales:
 *
 *      STR: 38
 *      DEF: 18
 *      STA: 60
 *
 *  - > Equipment:
 *
 *      - Staff
 *      - Leather Armor
 *
 ***/

public class Wizard extends Character{

    public Wizard(String name){
        super(name);
        setStats(new Stats(Wizard.class));
        setWeapon(new Staff());
        setArmor(new LeatherArmor());
    }

    public Wizard(){
        setStats(new Stats(Wizard.class));
    }

    @Override
    public String mainInfo(){
        String msgFmt = "\n\t>>>> %s <<<<\n Class: Wizard\n ID: %d\n Level: %d";
        return String.format(msgFmt, getName(), getId(), getLevelSystem().getLevel());
    }

    @Override
    public String toString(){
        String msg = "\t>>>> "+ getName()+" <<<<\n Class: Wizard";
        return msg.concat(super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof Wizard);
        }
        return false;
    }
}
