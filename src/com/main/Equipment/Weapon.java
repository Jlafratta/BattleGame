package com.main.Equipment;

/**
 *                 [ Clase padre de armas ]
 *
 *  - > Cada arma tendra un daño particular que sera definido
 *  mediante calculateDmg()
 *
 *  - > Golpe critico: 50% mas de daño (5% chance de obtenerlo)
 *
 *  - > El daño varia un 10% para que sea mas dinamico
 *
 *  - > Se espera agregar "power ups" a estas mediante la
 *  aplicacion de items para mejorarlas
 *
 *
 ***/

public abstract class Weapon {

    //Constantes
    private int baseDmg = 10;                         //Daño base
    protected final float criticalDmg = 1.50F;              //Daño critico
    private boolean criticalUpgrade = false;
    private boolean dmgUpgrade = false;

    private boolean criticalRate = Math.random() < 0.05;         //Probabilidad de obtener daño critico

    protected float dmgMod;                                 //Daño adicional del arma

    // Getter & Setter
    public int getBaseDmg() {
        return this.baseDmg;
    }

    public void setBaseDmg(int baseDmg) {
        this.baseDmg = baseDmg;
    }

    public boolean isDmgUpgrade() {
        return dmgUpgrade;
    }

    public void setDmgUpgrade(boolean dmgUpgrade) {
        this.dmgUpgrade = dmgUpgrade;
    }

    public float getCriticalDmg() {
        return this.criticalDmg;
    }

    public boolean isCriticalRate() {
        return this.criticalRate;
    }

    public float getDmgMod() {
        return this.dmgMod;
    }

    public boolean isCriticalUpgrade() {
        return criticalUpgrade;
    }

    public void setCriticalUpgrade(boolean criticalUpgrade) {
        this.criticalUpgrade = criticalUpgrade;
    }

    public void setCriticalRate(boolean criticalRate) {
        this.criticalRate = criticalRate;
    }

    // Abstracts
    protected abstract void setDmgMod();                    //Determina daño adicional del arma
    public abstract int calculateDmg();                //Determina daño total del arma

    @Override
    public String toString(){
        return "\t[ Weapon ]\n";
    }


}