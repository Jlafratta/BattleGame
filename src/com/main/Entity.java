package com.main;

/****************************************************************************************************

    Clase creada unicamente para poder unir en un arbol de herencia a Character y Npc

    y asi logar que el metodo principal donde se da la batalla ( battle(Object enemyfound) )

    pueda hacerse "pseudo-generico" con Object como parametro.

    Si hay tiempo se eliminara esta clase y se tratara de trabajar con genericos para que sea mas elegante.

    Que la fuerza nos acompañe.

****************************************************************************************************/


 public abstract class Entity {

    private int id;
    private static int idCont = 0;
    private String name;
    private boolean isAlive = true;

    public Entity(){
        setId(getIdCont());
    }

    private static int getIdCont() {
        return idCont++;
    }

    /** Getters & Setters **/

    private void setId ( int id){
        this.id = id;
    }

    public int getId () {
        return this.id;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public abstract String battleStats();

    public void die(){
        setAlive(false);
    }

    public void respawn() throws Exception{
        setAlive(true);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
