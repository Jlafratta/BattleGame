package com.main;

/**
 *                [ Interfaz Action ]
 *
 *  - > Interfaz que le define cierto comportamiento
 *  a los Character´s y Npc´s
 *
 ***/

import com.main.Characters.Character;

public interface Action {

    int attackChar(Character character);
    int receiveAttack(int dmg);
}
