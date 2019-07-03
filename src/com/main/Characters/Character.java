package com.main.Characters;

import com.main.Action;
import com.main.Entity;
import com.main.Equipment.*;
import com.main.Items.Inventory;
import com.main.Items.Item;
import com.main.Npcs.Npc;
import com.main.iHelper;

 /**
  *     [ Clase padre de todos los Personajes ]
  *
  *  - > Constructor sobrecargado en todas las subclases para
  *  permitir el correcto funcionamiento de las utilidades
  *  del Helper.
  *
  *  - > Tanto el daño como la resistencia son producto del
  *  Stat correspondiente ( Defense-Strenght ) por el 10%
  *  del daño proporcionado por la armadura y el arma propias
  *  del Personaje.
  *
  *  - > Varios setters encapsulados en Protected debido a que
  *  no deberian sufrir modificaciones externas.
  *
  ***/

public abstract class Character extends Entity implements Action, iHelper {

    private LevelSystem levelSystem;
    private Stats stats;
    private Weapon weapon;
    private Armor armor;
    private Inventory inv;

    public Character(String name){
        super();
        setName(name);
        setLevelSystem(new LevelSystem());
        setInv(new Inventory());
    }

     public Character(){

     }

    // METHODS
    private int getResistance(){
        return (int)(getStats().getDefense()*(getArmor().calculateArmor()*0.1));
    }

    private int getDamage(){
        return (int)(getStats().getStrength()*(getWeapon().calculateDmg()*0.10));
    }

    public int attackNpc(Npc npc){
        return npc.receiveAttack(getDamage());
    }

    public int attackChar(Character character){
        return character.receiveAttack(getDamage());
    }

    public int receiveAttack(int dmg){

        int damage = 0;

        if(dmg > getResistance()){                                  //Si el daño supera la resistencia, recibe el daño
            damage = dmg - getResistance();                         //Aplicacion de la resistencia
            if(getStats().getHealth()<=damage){                     //Si el daño supera la vida, esta queda en 0
                getStats().setHealth(0);
            }else{
                getStats().setHealth(getStats().getHealth()-damage);    //Aplicacion del daño

            }
        }
        return damage;  //retorna el daño causado
    }

    // Agrega el drop que deja el npc al derrotarlo. Controla las excepciones de sus metodos
    public void getDrop(int gold, int exp, Item item) throws Exception{
        try {
            if (item!=null){
                getInv().addItem(item);
            }
            getLevelSystem().addExp(exp);
            getInv().addGold(gold);
        }catch (Exception e){
            System.out.println(e.getMessage());
            helper.pressAnyKeyToContinue();
        }
    }

    private void looseDrop()throws Exception{
        getInv().extractGold(getInv().getGold()/10); //Al morir pierde un 10% del oro
    }

    /// GETTERS & SETTERS

    public LevelSystem getLevelSystem() {
        return levelSystem;
    }

    protected void setLevelSystem(LevelSystem levelSystem) {
        this.levelSystem = levelSystem;
    }

    public Stats getStats () {
        return this.stats;
    }

    protected void setStats (Stats stats){
        this.stats = stats;
    }

    public Weapon getWeapon () {
        return this.weapon;
    }

    public void setWeapon (Weapon weapon){
        this.weapon = weapon;
    }

    public Armor getArmor () {
        return this.armor;
    }

    public void setArmor (Armor armor){
        this.armor = armor;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    // MUESTREOS
    public String battleStats(){
        String msgFmt = "\t>>>> %s <<<<\n\t | HP | %d/%d |";
        return String.format(msgFmt, getName(),getStats().getHealth(), getStats().getStamina());
    }

    public abstract String mainInfo();

    @Override
    public void respawn() throws Exception{
        super.respawn();                               // Llama al super proveniente de Entity que setea isAlive = true
        getStats().setHealth(getStats().getStamina()); // Recupera la vida al 100% al respawnear
        looseDrop();                                   // pierde loot al respawnear
    }

    @Override
    public String toString(){
        String msgFmt = "\n ID: %d\n %s\n %s\n Point: %s\n\n %s\n %s";
        return String.format(msgFmt, getId(), getLevelSystem().toString(), getStats().toString(), getLevelSystem().getLevelPoint(),getWeapon().toString(), getArmor().toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Character)){
            return false;
        }else if (((Character)obj).getId() != getId()){
            return false;
        }
        return true;
    }
    }

