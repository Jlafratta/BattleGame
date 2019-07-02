package com.main.Characters;

import com.main.iHelper;

public class LevelSystem implements iHelper {

    private final int maxLevel = 15;
    private final int ppl = 3;
    private int level;
    private int exp;
    private int expTop;
    private int levelPoint;

    // CONSTRUCTOR

    public LevelSystem (){
        setLevel(1);
        setLevelPoint(0);
        setExp(0);
        setExpTop(100);
    }

    // METHODS

    // Aumenta de nivel validando que no haya llegado al nivel maximo mediante Exception
    private void levelUP(int ex)throws Exception{
        if (getLevel() == getMaxLevel()){
            throw new Exception("\n\t\tYou have reached max level\n");
        }else {
            setLevel(getLevel()+1);
            setExpTop(getExpTop() * 2);
            setLevelPoint(getLevelPoint() + getPpl());
            addExp(ex);
        }
    }

    // Añade experiencia validando que no tenga valor negativo mediante Exception
    public void addExp(int exp){
        try {
            if (exp<0){
                throw new Exception("\n\t\tExp value error (-)\n");
            }else {
                setExp(getExp() + exp);
                if(getExp() >= getExpTop()){        // Si completa la experiencia del nivel actual
                    int ex = getExp()-getExpTop();  // Determina el excedente de exp que se sumaria al prox lvl
                    setExp(getExpTop());            // Agrega la exp faltante en este nivel
                    levelUP(ex);                    // le pasa el excedente a levelUp que aumenta el nivel y vuelve a llamar
                }                                   // a addExp para agregar el excedente
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            helper.pressAnyKeyToContinue();
        }

    }

    public void applyPoints(int points){
        setLevelPoint(getLevelPoint()-points);
    }

    // GETTER & SETTER


    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLevel() {
        return level;
    }

    private void setLevel(int level) {
        this.level = level;
    }

    public int getExp() {
        return exp;
    }

    private void setExp(int exp) {
        this.exp = exp;
    }

    public int getExpTop() {
        return expTop;
    }

    private void setExpTop(int expTop) {
        this.expTop = expTop;
    }

    public int getLevelPoint() {
        return levelPoint;
    }

    private void setLevelPoint(int levelPoint) {
        this.levelPoint = levelPoint;
    }

    public int getPpl() {
        return ppl;
    }

    @Override
    public String toString(){
        String msgFmt = "Level: %d\n XP | %d / %d |\n";
        return String.format(msgFmt, getLevel(),getExp(), getExpTop());
    }
}
