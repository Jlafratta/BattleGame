package com.main.Maps;

import com.main.Npcs.Npc;

import java.util.List;

/**
 *                     [ Clase Map ]
 *
 *  - > Clase padre de los distintos tipos de mapas a los que
 *  podra moverse el Usuario para encontrarse con los Npc´s
 *  que estos mismos contienen. Cada uno tiene una dificultad
 *  distinta (tier) que determina la dificultad de sus Npc´s
 *
 *  - > La operacion de cambio de mapa no se encuentra en esta
 *  clase debido a que se considera al mapa como un objeto que
 *  no tiene un comportamiento para modificarse a si mismo, mas
 *  que asignarse los Npc´s que le corresponden en su Constructor.
 *  El mapa no se cambia a si mismo en el sistema, sino que el
 *  Usuario decide cambiar el mapa en el que se encuentra
 *  posicionado en BattleGame.
 *
 *  - > Debido a la baja cantidad de atributos, se utiliza el
 *  equals() por default, que no genero ningun problema en la
 *  ejecución
 *
 ***/

public abstract class Map {

    private final int moveCost = 15;    //costo basico de transporte hacia el mapa
    private List<Npc> npcList;

    protected abstract void addNpc(Class Npc);
    protected abstract void createNpcList();
    public abstract int getTier();

    public int getMoveCost() {      // retorna el costo general*tier
        return moveCost*getTier();
    }

    public List<Npc> getNpcList() {
        return npcList;
    }

    protected void setNpcList(List<Npc> npcList) {
        this.npcList = npcList;
    }

    public void showNpcList() throws Exception{

        if (getNpcList().isEmpty() || getNpcList() == null){
            throw new Exception("\n\t No Npcs founded\n");
        }

        for (Npc n : getNpcList()){
            System.out.println(" -> "+n.getName()+" Tier "+n.getTier());
        }
    }

}
