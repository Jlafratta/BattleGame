package com.main.Equipment;

/**
 *                 [ Sword ]
 *
 *  - > Arma predeterminada del Archer
 *
 ***/

public class Bow extends Weapon {

    private final int arrowDmg = 65;

    public Bow(){
        setDmgMod();
    }

    public int getArrowDmg() {
        return arrowDmg;
    }

    @Override
    protected void setDmgMod() {
        this.dmgMod = getArrowDmg();
    }

    @Override
    public int calculateDmg() {

        float dmg = getBaseDmg()+getDmgMod();
        dmg =(float)(Math.random()*(dmg - ((dmg*0.9))+1)+(dmg*0.9));          //Variacion del 10% en el daño
        //APLICAR DESPUES DE TEST DE BATALLA
        dmg = isCriticalRate() ? dmg * getCriticalDmg() : dmg;                  //Daño critico

        return (int)dmg;    //retorno casteado a int para trabajar todo en enteros
    }

    @Override
    public String toString(){
        String base = super.toString();

        return base.concat(String.format(" Type: %sBow\n Damage: %d-%d %s %s", (isCriticalRate()&&isDmgUpgrade()) ? "Legendary ": "" ,(int)((getBaseDmg()+getDmgMod())*0.9), getBaseDmg()+(int)getDmgMod(),
                isCriticalUpgrade() ? "\n Critical rate increase +15%" : "", isDmgUpgrade() ? "\n Additional damage +25" : ""));
        //Daño minimo comentado hasta aplicar la variacion del 10%
    }
}
