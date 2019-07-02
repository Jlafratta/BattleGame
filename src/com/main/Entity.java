package com.main;

/****************************************************************************************************

    Clase creada unicamente para poder unir en un arbol de herencia a Character y Npc

    y asi logar que el metodo principal donde se da la batalla ( battle(Object enemyfound) )

    pueda hacerse "pseudo-generico" con Object como parametro.

    Si hay tiempo se eliminara esta clase y se tratara de trabajar con genericos para que sea mas elegante.

    Que la fuerza nos acompañe.

****************************************************************************************************/


 public abstract class Entity {

    private String name;
    private boolean isAlive = true;

    /** Getters & Setters **/

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public abstract String battleStats();

    public void die(){
        this.isAlive = false;
    }

    public void respawn() throws Exception{
        this.isAlive = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
