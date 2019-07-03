package com.main.Equipment;

/**
 *                 [ Clase padre de armaduras ]
 *
 *  - > Cada armadura tendra una defensa particular que sera
 *  definida con calculateArmor()
 *
 *  - > Se espera agregar "power ups" a estas mediante la
 *  aplicacion de items del inventario para mejorarlas
 *
 ***/

public abstract class Armor {

    private int baseDef = 5;
    private boolean defUpgrade = false;
    protected float defMod;

    public boolean isDefUpgrade() {
        return defUpgrade;
    }

    public void setDefUpgrade(boolean defUpgrade) {
        this.defUpgrade = defUpgrade;
    }

    public void setBaseDef(int baseDef) {
        this.baseDef = baseDef;
    }

    public int getBaseDef() {
        return baseDef;
    }

    public float getDefMod() {
        return defMod;
    }

    protected abstract void setDefMod();
    public abstract int calculateArmor();

    @Override
    public String toString(){
        return "\n\t[ Armor ]\n";
    }
}
