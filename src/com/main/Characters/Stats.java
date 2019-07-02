package com.main.Characters;

/**
 *                  [ Stats de los Personajes ]
 *
 *  - > STR: Fuerza
 *  - > DEF: Defensa
 *  - > STA: Puntos de vida
 *
 *  - > Cada punto asignado a STR o DEF incrementa este mismo en 1.
 *
 *  - > Cada punto asignado a STA incrementa esta misma en un 15%
 *
 *  - > Se utiliza Health para la vida actual y variable del Personaje
 *  y Stamina para el tope de vida total del personaje.
 *
 *  - > No se implementa exception, debido a que las validaciones son
 *  simples de resolver mediante while o for, para no interrumpir la
 *  ejecucion del programa.
 *
 ***/

public class Stats {


    private int strength;
    private int defense;
    private int stamina;
    private int health;

    public Stats(Class Character){

        if(Character == Knight.class){
            setStrength(27);          //Stats propios del Knight
            setDefense(21);
            setStamina(110);
            setHealth(110);
        }else if (Character == Archer.class){
            setStrength(32);          //Stats propios del Archer
            setDefense(20);
            setStamina(80);
            setHealth(80);
        }else if (Character == Wizard.class){
            setStrength(38);          //Stats propios del Wizard
            setDefense(18);
            setStamina(60);
            setHealth(60);
        }
    }


    public void addStr(int strPoint){                       //Cada punto aumentado incrementa 1 el str
        if (strPoint > 0){
            setStrength(getStrength()+strPoint);
        }
    }

    public void addDef(int defPoint){                       //Cada punto aumentado incrementa 1 la def
        if (defPoint > 0){
            setDefense(getDefense()+defPoint);
        }
    }

    public void addSta(int staPoint){                       //Cada punto aumentado incrementa 15% sta
        if (staPoint > 0) {
            setStamina(getStamina()+(int)(getStamina()*0.15)*staPoint);
            setHealth(getStamina());
        }
    }

    public int getStrength() {
        return this.strength;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getStamina() {
        return this.stamina;
    }

    public int getHealth() {
        return this.health;
    }

    private void setStrength(int strength) {
        this.strength = strength;
    }

    private void setDefense(int defense) {
        this.defense = defense;
    }

    private void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void setHealth(int health) {
        this.health = health;
    }


    @Override
    public String toString(){
        String msgFmt = "\t<< Stats >>\n STR: %d\n DEF: %d\n STA: %d/%d";
        return String.format(msgFmt, getStrength(), getDefense(), getHealth(), getStamina());
    }
}