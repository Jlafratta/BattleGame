package com.main.Items;

import com.main.Characters.Character;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private final int space = 15;
    private List<Item> itemList;
    private int gold;

    public Inventory(){
        setGold(0);
        setItemList(new ArrayList<>());
    }

    public int getSpace() {
        return space;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    private void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void showInventory(){

        System.out.println();

        if (getItemList().isEmpty()){
            System.out.println("\n\t\t Backpack is empty\n");
        }else {
            System.out.println("|=============== Backpack ===============|\n");
            for (Item i : getItemList()) {
                System.out.println(" ________________________\n");
                System.out.println("  [ "+(getItemList().indexOf(i)+1)+" ]\n");
                System.out.println(i.toString());
            }
            System.out.println(" ________________________");
            System.out.println("\n|========================================|\n");
        }
    }

    public int getGold() {
        return gold;
    }

    private void setGold(int gold) {
        this.gold = gold;
    }

    // Agrega un item validando que haya espacio. Solo se utiliza si item no es null ( validado anteriormente )
    public void addItem(Item i) throws Exception{
        if (getItemList().size()<getSpace()){
            getItemList().add(i);
        }else {
            throw new Exception("\n\t\tInventory is full !\n");
        }
    }

    // Agrega oro validando que el valor no sea negativo mediante Exception
    public void addGold(int g) throws Exception{
        if (g<0){
            throw new Exception("\n\t\tGold value error (-)\n");
        }else {
            setGold(getGold()+g);
        }
    }

    // Retira oro validando que tenga el suficiente mediante Exception
    public void extractGold(int g) throws Exception{
        if (getGold()<g){
            throw new Exception("\n\t\t You dont have enough Gold\n");
        }else {
            setGold(getGold()-g);
        }
    }

    public void useItem(Character player, Object item){

        try {
            if (item instanceof HealthPotion){
                ((HealthPotion)item).applyItem(player);
            }else if (item instanceof CriticalGem){
                ((CriticalGem)item).applyItem(player);
                getItemList().remove(item);
                System.out.println("\n Succesfully upgraded !\n");
                System.out.println(player.getWeapon().toString());
            }else if (item instanceof DamageGem){
                ((DamageGem)item).applyItem(player);
                getItemList().remove(item);
                System.out.println("\n Succesfully upgraded !\n");
                System.out.println(player.getWeapon().toString());
            }else if (item instanceof DefenseGem){
                ((DefenseGem)item).applyItem(player);
                getItemList().remove(item);
                System.out.println("\n Succesfully upgraded !\n");
                System.out.println(player.getArmor().toString());
            }

        }catch (Exception e){
            if (e.getMessage().equals("\n\t\t\tItem fully used\n")){ // si se lanza la excepcion de que se termino el stack
                getItemList().remove((HealthPotion)item);                          // se quita el item
                System.out.println(e.getMessage());
                System.out.println("|=========================================|\n");
            }else {
                System.out.println(e.getMessage());
                System.out.println("\n|=========================================|\n");
            }
        }
    } // Utiliza un item. Controla sus excepciones

    @Override
    public String toString(){
        String msgFmt ="\n\t Backpack: %d / %d\n\t Gold: %d";
        return String.format(msgFmt, getItemList().size(), getSpace(), getGold());
    }
}
