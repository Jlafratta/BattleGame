package com.main.Items;

import com.main.Characters.Character;

public abstract class Item {
    private static int idCont = 0;
    private int id;

    public Item(){
        setId(getIdentifier());
    }

    public abstract String getName(); // utilizado para reconocerlo en el drop

    private static int getIdentifier(){
        idCont++;
        return idCont;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    protected abstract void applyItem(Character character) throws Exception;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Item){
            return true;
        }
        return false;
    }
}
