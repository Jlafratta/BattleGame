package com.main.Items;

import com.main.Characters.Character;

/**
 *                   [ Pocion de vida ]
 *
 *   - > Recupera el 50% de los puntos de vida totales
 *   al utilizse.
 *
 *   - > Se implementa un sistema acumulativo de "stacks",
 *   para que puedan tenerse hasta 15 items de este tipo
 *   "stackeados", por lo que solo ocuparía 1 slot en el
 *   inventario.
 *
**/


public class HealthPotion extends Item{

    private final float restoration = 0.50f;
    private final int stackLimit = 15;
    private int stack;

    // Constructors

    public HealthPotion(){      // Crea una pocion
        super();
        setStack(1);
    }

    public HealthPotion(int stack){     // Crea una o mas pociones, segun parametro
        super();
        setStack(stack);
    }

    public void increaseStack(int stack){            // Incrementa stack
        if(getStack()<=getStackLimit()){
            setStack(getStack()+stack);
        }
    }

    public int getStackLimit() {
        return stackLimit;
    }

    public float getRestoration() {
        return restoration;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    @Override
    public void applyItem(Character character) throws Exception{

        if (character.getStats().getHealth() == character.getStats().getStamina()){ // valida que la vida no este al 100%
            throw new Exception("\n\t\tHealth is full!");
        }else if (getStack() == 0){
            throw new Exception("\n\t Stack error (0)");
        }

        int pot = (int)(character.getStats().getHealth()+(character.getStats().getStamina()*getRestoration()));
        // pot: cantidad de vida que tendra al curarse

        if (pot >= character.getStats().getStamina()){
            System.out.println("\n\t+ "+(pot-character.getStats().getStamina())+" HP\n");
            character.getStats().setHealth(character.getStats().getStamina()); // si pot supera o iguala a la sta
        }else{                                                                 // deja la vida al 100%
            System.out.println("\n\t+ "+(pot-character.getStats().getHealth())+" HP\n");
            character.getStats().setHealth(pot);                         //sino, simplemente restaura lo que corresponda
        }


        setStack(getStack()-1); // resto 1 del stack al utilizarla
        if (getStack() == 0){
            throw new Exception("\n\t\t\tItem fully used\n");
        }
    }

    public String getName(){
        return " [ Health Potion ] ";
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)){
            return (obj instanceof HealthPotion);
        }
        return false;
    }

    @Override
    public String toString(){
        String msgFmt = " > Health Potion (%d)\n\n   Restores life %% %.0f ";
        return String.format(msgFmt, getStack(), getRestoration()*100 );
    }
}
