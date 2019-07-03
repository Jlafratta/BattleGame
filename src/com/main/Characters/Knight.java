package com.main.Characters;

import com.main.Equipment.*;
import com.main.Items.Inventory;

/**
 *          [ Caballero ]
 *
 *  - > Stats inciciales:
 *
 *      STR: 27
 *      DEF: 21
 *      STA: 110
 *
 *  - > Equipment:
 *
 *      - Sword
 *      - Golden Armor
 *
 ***/

public class Knight extends Character{

    public Knight(String name){
        super(name);
        setStats(new Stats(Knight.class));
        setWeapon(new Sword());
        setArmor(new GoldenArmor());
    }

    public Knight(){
        setStats(new Stats(Knight.class));
    }

    @Override
    public String mainInfo(){
        String msgFmt = "\n\t>>>> %s <<<<\n Class: Knight\n ID: %d\n Level: %d";
        return String.format(msgFmt, getName(), getId(), getLevelSystem().getLevel());
    }

    @Override
    public String toString(){
        String msg = "\t>>>> "+ getName()+" <<<<\n Class: Knight";
        return msg.concat(super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof Knight);
        }
        return false;
    }

}