package com.main.Maps;

import com.main.Npcs.*;
import java.util.ArrayList;

/**
 *                 [ Map - Tarkan ]
 *
 *  - > Npc's: tier 2
 *
 ***/

public class Tarkan extends Map {

    private final int tierTarkan = 2;

    public Tarkan(){
        setNpcList(new ArrayList<>());
        createNpcList();
    }

    // Carga el array con los npc predeterminados (eliminar al implementar archivos)
    public void createNpcList(){
        addNpc(Dragon.class);
        addNpc(Elf.class);
        addNpc(Warrior.class);
    }

    // Se utiliza en createNpcList para cargar los npc predeterminados
    // Falta agregar validacion de que no exista el npc en el array con exception
    public void addNpc(Class Npc){
        if (Npc == Dragon.class){
            getNpcList().add(new Dragon(getTier()));
        }else if (Npc == Warrior.class){
            getNpcList().add(new Warrior(getTier()));
        }else if (Npc == Elf.class){
            getNpcList().add(new Elf(getTier()));
        }
    }

    public int getTier() {
        return tierTarkan;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Tarkan);
    }

    @Override
    public String toString(){
        return " [ Tarkan ]";
    }
}
