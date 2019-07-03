package com.main;

import com.main.Characters.Character;
import com.main.Characters.*;
import com.main.Npcs.Npc;

/**
 *                      [ Helper ]
 *
 *  - > Brinda metodos que son de gran utilidad para la
 *  ejecucion del programa en distintas clases.
 *
 *      -clearSpace(): simula una "limpieza" de pantalla
 *    en la consola.
 *
 *      -pressAnyKeyToContinue(): le indica al usuario
 *    que presione la tecla 'Enter' para poder continuar
 *    con la ejecucion del programa.
 *
 *      -showBattleStats(): printea los stats del jugador
 *    y del oponente. Utilizado en el paso a paso de los
 *    combates
 *
 *      -showAllClass(): printea todos los tipos de clases
 *    y sus Stats predeterminados. Utilizado en la
 *    seleccion de clase para la creacion de un nuevo
 *    Character.
 *
 *      -printWithSleep(): recibe un String y le printea
 *    3 puntos suspensivos con un intervalo de 0,5 seg
 *    entre cada uno de ellos.
 *    Utilizado en operaciones como buscar un enemigo
 *    o respawnear.
 *
 *      -printWithRandomSleep(): misma parametrizacion
 *    que printWithSleep(), solo que el intervalo sera
 *    de 0 a 4 seg aleatoriamente.
 *
 ***/

public class Helper {

    public void clearSpace(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void pressAnyKeyToContinue() {
        System.out.println("Press Enter key to continue...");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    public void showBattleStats(Character pj, Object enemy){

        System.out.println("\n----------------------------");
        System.out.println(pj.battleStats());
        System.out.println("-------------VS-------------");
        System.out.println(enemy instanceof Npc ? ((Npc) enemy).battleStats() : ((Character)enemy).battleStats() );
        System.out.println("----------------------------\n");
    }

    public void showAllClass(){
        Character knight = new Knight();
        Character wizard = new Wizard();
        Character archer = new Archer();

        System.out.println("    \t >1<\t\t >2<\t\t >3<");
        System.out.println("      Knight   |   Wizard  |   Archer\n");
        System.out.println(String.format("      STR: %d  |  STR: %d  |  STR: %d",knight.getStats().getStrength(), wizard.getStats().getStrength(), archer.getStats().getStrength()));
        System.out.println(String.format("      DEF: %d  |  DEF: %d  |  DEF: %d",knight.getStats().getDefense(), wizard.getStats().getDefense(), archer.getStats().getDefense()));
        System.out.println(String.format("      STA: %d |  STA: %d  |  STA: %d",knight.getStats().getStamina(), wizard.getStats().getStamina(), archer.getStats().getStamina()));

    }

    public void printWithSleep(String msg){
        try {
            System.out.print(msg);
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
            System.out.print(".");
            Thread.sleep(500);
        }catch (Exception e){
            System.out.println("\n\tSleep error: "+ e.getMessage());
        }
    }

    public void printWithRandonSleep(String msg){
        try {
            int rand = (int) (Math.random()*4000);     //radom entre 5 seg
            System.out.print(msg);
            Thread.sleep(rand);
            System.out.print(".");
            Thread.sleep(rand);
            System.out.print(".");
            Thread.sleep(rand);
            System.out.print(".");
            Thread.sleep(rand);
        }catch (Exception e){
            System.out.println("\n\tSleep error: "+ e.getMessage());
        }
    }
}
