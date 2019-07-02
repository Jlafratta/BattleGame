package com.main.Npcs;

import com.main.Action;
import com.main.Characters.Character;
import com.main.Entity;
import com.main.Items.*;

public abstract class Npc extends Entity implements Action{

    private int id;
    private static int idCont = 0;
    private int dmg;
    private int def;
    private int sta;
    private int exp;
    private int gold;
    private int tier;
    private Item drop = null;

    /** Constructor **/

    public Npc(){
        setId(getIdentifier());
    }

    /** Methods **/

    // Setea Defensa y Stamina segun el Npc que se este creando
    protected void setNpcStats(Class Npc){
        if (Npc == Dragon.class){
            setDef(50*tier);
            setSta(800*tier);
        }else if (Npc == Elf.class){
            setDef(30*tier);
            setSta(300*tier);
        }else if (Npc == Warrior.class){
            setDef(70*tier);
            setSta(400*tier);
        }
    }

    public static int getIdentifier(){
        idCont ++;
        return idCont;
    }

    public int attackChar(Character character){
        return character.receiveAttack(calculateDmgNpc());
    }

    public int receiveAttack(int dmg){

        int damage = 0;

        if(dmg > getDef()){                                  //Si el daño supera la defensa, recibe el daño
            damage = dmg - getDef();                         //Aplicacion de la defensa
            if(getSta()<=damage){                            //Si el daño supera la vida, esta queda en 0
                setSta(0);
            }else{
                setSta(getSta()-damage);                     //Aplicacion del daño
            }
        }
        return damage;  //retorna el daño causado
    }

    /** Getters & Setters **/

    public static int getIdCont() {
        return idCont++;
    }

    public Item getItem() {

        boolean critGem = Math.random() < 0.05;
        boolean dmgGem = Math.random() < 0.05;
        boolean defGem = Math.random() < 0.05;
        boolean potion = Math.random() < 0.30;

        if (critGem){
            return new CriticalGem();
        }else if (dmgGem){
            return new DamageGem();
        }else if (defGem){
            return new DefenseGem();
        }else if (potion){
            return new HealthPotion();
        }else return null;
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public int getDmg() {
        return dmg;
    }

    protected void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getDef() {
        return def;
    }

    protected void setDef(int def) {
        this.def = def;
    }

    public int getSta() {
        return sta;
    }

    protected void setSta(int sta) {
        this.sta = sta;
    }

    //Variacion del 10% de exp aplicada
    public int getExp() {
        return (int)Math.floor(Math.random()*(exp - ((exp*0.9))+1)+(exp*0.9));
    }

    protected void setExp(int exp) {
        this.exp = exp;
    }

    //Variacion del 10% de exp aplicada
    public int getGold() {
        return (int)Math.floor(Math.random()*(gold - ((gold*0.9))+1)+(gold*0.9));
    }

    protected void setGold(int gold) {
        this.gold = gold;
    }

    public int getTier() {
        return tier;
    }

    protected void setTier(int tier) {
        this.tier = tier;
    }

    /** Abstract **/

    public abstract int calculateDmgNpc();

    // Show stats in a battle
    public String battleStats(){
        String msgFmt = "\t\t-=> %s <=-\n\t| Tier: %d | HP | %d |";
        return String.format(msgFmt, getName(), getTier(),getSta());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Npc){
            return (((Npc)obj).getId() == getId());
        }
        return false;
    }

    @Override
    public String toString(){
        String msgFmt = "\n DMG: %d\n RES: %d\n HP: %d";
        return String.format(msgFmt, getDmg(), getDef(), getSta());
    }
}
