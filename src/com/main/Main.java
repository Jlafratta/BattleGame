package com.main;


import com.main.Characters.Knight;

/**
 *             -=[ Trabajo Practico > Battle Game < ]=-
 *
 *    Descripcion:
 *
 *      - > El siguiente programa consiste en el desarrollo de
 *    un juego en el cual el Usuario podra crear su personaje
 *    para poder enfrentarse a diversos enemigos, asi tambien
 *    como otros personajes creados, y desarrollarse durante
 *    la ejecucion de este.
 *
 *
 *  Integrantes:
 *
 *  > Menillo, Matías <
 *  > Lafratta, Julián <
 *  > Sassano, Luciano <
 *
 ***/

public class Main implements iHelper{

    public static void main(String[] args) {

        BattleGame BattleG_UTN = new BattleGame();

        BattleG_UTN.start();
    }
}
