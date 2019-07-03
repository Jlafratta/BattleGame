package com.main;

import com.main.Maps.*;

/**
 *                           [ Menu System ]
 *
 *  - > Clase encargada del manejo del flujo del programa. Comienza en
 *  startMenu() y reacciona en base al comportamiento del Usuario.
 *
 *  - > Contiene un BattleGame o 'Server' que se le enviara por
 *  parametro al Constructor, con todos los datos necesarios para
 *  la ejecucion del programa.
 *
 ***/

public class SystemMenu implements iHelper{

    private BattleGame sv;

    /** Constructor **/
    // Recibe el BattleGame sobre el cual trabajara
    public SystemMenu(BattleGame sv){
        setSv(sv);
    }

    /** Menus **/

    // No tiene manejo de excepciones ya que controla las entradas de datos con scan.hasNextInt
    // y son solo ingresos de datos para ingresar a otros menu
    public void startMenu(){

        int value = -1;

        while (value != 0){
            helper.clearSpace();
            System.out.println("\n|========= Welcome to BattleGame =========|\n");
            System.out.println(" 1.- Create new character");
            System.out.println(" 2.- Choose a character");
            System.out.println(" 3.- Server info");
            System.out.println(getSv().getPlayer() != null ? " 4.- Enter as "+ getSv().getPlayer().getName(): " ");
            System.out.println("\n 0.- Exit");
            System.out.println("\n|=========================================|");
            System.out.print("\n -> ");

            while(!scan.hasNextInt()) {
                helper.clearSpace();
                System.out.println("\n|========= Welcome to BattleGame =========|\n");
                System.out.println(" 1.- Create new character");
                System.out.println(" 2.- Choose a character");
                System.out.println(" 3.- Server info");
                System.out.println("\n 0.- Exit");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");
                scan.next();
            }

            value = scan.nextInt();

            switch (value) {
                case 1:
                    newCharacterMenu();
                    break;
                case 2:
                    chooseCharacterMenu();
                    break;
                case 3:
                    infoMenu();
                    break;
                case 4:
                    inGameMenu();
                    break;
                case 0:
                    helper.clearSpace();
                    System.out.println("|=========================================|");
                    System.out.println(" \t\t\tThanks for coming");
                    System.out.println("|=========================================|\n");
                    break;
                default: break;
            }
        }
    }

    // Menu de creacion de pj / Controla excepcion si el nombre esta en uso o si el tipo de clase es erroneo
    private void newCharacterMenu() {
        try {
            helper.clearSpace();
            System.out.println("\n|============= New Character =============|\n");
            System.out.println("\t\t > Name <");
            System.out.print("\n -> ");
            String name = scan.next();

            while (name.length()>10 || name.length()<4){
                helper.clearSpace();
                System.out.println("\n|============= New Character =============|\n");
                System.out.println("\t\t > Name <     max 10-min 4 digits!");
                System.out.print("\n -> ");
                name = scan.next();
            }

            System.out.println("\n \t\t\t > Choose class <\n");
            helper.showAllClass();
            System.out.print("\n -> ");

            while(!scan.hasNextInt()) {
                helper.clearSpace();
                System.out.println("\n|============= New Character =============|\n");
                System.out.println("\t\t > Name <");
                System.out.print("\n -> "+name+"\n");
                System.out.println("\n \t\t\t > Choose class <\n");
                helper.showAllClass();
                System.out.println("\n\t Please insert a number.");
                System.out.print("\n -> ");
                scan.next();
            }
            int value = scan.nextInt();

            getSv().addPlayer(getSv().newPlayer(name, value)); //Agregacion. Validaciones con exception
            //addPlayer verifica el nombre / newPlayer verifica la
            inGameMenu();

        }catch (Exception e){
            System.out.println("\n|=========================================|");
            System.out.println(e.getMessage());
            System.out.println("|=========================================|\n");
            helper.pressAnyKeyToContinue();
        }
    }

    // Menu de eleccion de pj / Controla excepcion si no hay pj disponibles o el pj seleccionado no existe.
    private void chooseCharacterMenu(){

        try {
            helper.clearSpace();
            System.out.println("\n|========= Choose your Character =========|");
            getSv().showPlayerList(); //Printea los pj. Exception controlada (showPlayerList)
            System.out.println("\n|=========================================|");
            System.out.println("\n >Enter name ");
            System.out.print("\n -> ");
            String name = scan.next();
            getSv().setPlayer(getSv().getPlayerByName(name));   //Settea el pj. Exception controlada(getPlayerByName)
            helper.clearSpace();
            System.out.println("\n >Selected character:\n");
            System.out.println(getSv().getPlayer().toString()); //Printea el pj seleccionado
            helper.pressAnyKeyToContinue();
            inGameMenu();   //Ingresa al menu de juego

        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("|=========================================|\n");
            helper.pressAnyKeyToContinue();
        }
    }

    // Muestra la version del juego y los pj / Controla excepcion si el array esta vacio.
    private void infoMenu(){
        helper.clearSpace();
        System.out.println("\n|============== Server Info ==============|");
        System.out.println("\t\t\t\t\t\t\t\t\tv"+ getSv().getVersion());
        System.out.println(getSv().toString());
        System.out.println("\n|=========================================|");

        try {
            getSv().showPlayerList(); //Printea los pj. Excepcion controlada (showPlayerList)
            System.out.println("\n|=========================================|\n");
            helper.pressAnyKeyToContinue();
        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("|=========================================|\n");
            helper.pressAnyKeyToContinue();
        }
    }

    // Menu de jugador
    private void inGameMenu() {

        if (getSv().getPlayer() != null) {

            int value = -1;

            while (value != 0) {
                helper.clearSpace();
                System.out.println("  "+ getSv().getActualMap().toString());
                System.out.println("|=============== Safe Zone ===============|");
                System.out.println("\n  " + getSv().getPlayer().getName() + " < " + getSv().getPlayer().getLevelSystem().getLevel() + " > \n");
                System.out.println(" 1.- Go to Battle Zone");
                System.out.println(" 2.- Inventory");
                System.out.println(" 3.- Equipment");
                System.out.println(" 4.- Stats");
                System.out.println(" 5.- Change map");
                System.out.println("\n 0.- Back to Start Menu");
                System.out.println("\n  XP: " + getSv().getPlayer().getLevelSystem().getExp() + " / " + getSv().getPlayer().getLevelSystem().getExpTop());
                System.out.println("|=========================================|");
                System.out.print("\n -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    System.out.println("  "+ getSv().getActualMap().toString());
                    System.out.println("|=============== Safe Zone ===============|");
                    System.out.println("\n  " + getSv().getPlayer().getName() + " <" + getSv().getPlayer().getLevelSystem().getLevel() + "> \n");
                    System.out.println(" 1.- Go to Battle Zone");
                    System.out.println(" 2.- Inventory");
                    System.out.println(" 3.- Equipment");
                    System.out.println(" 4.- Stats");
                    System.out.println(" 5.- Change map");
                    System.out.println("\n 0.- Back to Start Menu");
                    System.out.println("\n  XP: " + getSv().getPlayer().getLevelSystem().getExp() + " / " + getSv().getPlayer().getLevelSystem().getExpTop());
                    System.out.println("\n|=========================================|");
                    System.out.print("\n -> ");
                    scan.next();
                }

                value = scan.nextInt();

                switch (value) {
                    case 1:
                        try{
                            helper.clearSpace();
                            System.out.println("\n|=========================================|");
                            helper.printWithRandonSleep("\n\t\t\tSearching enemy");
                            getSv().searchBattle();
                        }catch (Exception e){
                            System.out.println("\n\t Error on Battle: "+e.getMessage());
                        }
                        break;
                    case 2:
                        inventoryMenu();
                        break;
                    case 3:
                        equipmentMenu();
                        break;
                    case 4:
                        statsMenu();
                        break;
                    case 5:
                        mapMenu();
                        break;
                    case 0:
                        System.out.println("\n|=========================================|");
                        helper.printWithSleep(" \t\t\tGoing to Start Menu");
                        break;
                    default:
                        break;
                }

            }
        }
    }

    // Menu de inventario. Permite ver y utilizar los items. Upgrades de equipamiento.
    private void inventoryMenu(){

        try {

            int value = -1;

            while (value != 0){

                helper.clearSpace();
                System.out.println("|=============== Inventory ===============|");
                System.out.println(getSv().getPlayer().getInv().toString());
                System.out.println("\n 1.- View backpack");
                System.out.println(" 2.- Use item");
                System.out.println("\n 0.- Back to Start Menu");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    System.out.println("|=============== Inventory ===============|");
                    System.out.println(getSv().getPlayer().getInv().toString());
                    System.out.println("\n 1.- View backpack");
                    System.out.println(" 2.- Use item");
                    System.out.println("\n 0.- Back to Start Menu");
                    System.out.println("\n|=========================================|");
                    System.out.print("\n -> ");
                    scan.next();
                }
                value = scan.nextInt();

                switch (value){
                    case 1:
                        helper.clearSpace();
                        getSv().getPlayer().getInv().showInventory();
                        helper.pressAnyKeyToContinue();
                        break;
                    case 2:
                        consumeItemMenu();
                        break;
                    case 0:
                        break;
                        default:break;
                }

            }

        }catch (Exception e){
            System.out.println("|=========================================|");
            System.out.println(e.getMessage());
            System.out.println("|=========================================|");
        }
    }

    // Menu de utilizacion de items
    private void consumeItemMenu(){
        try {

            int value = -1;

            while (value != 0){

                helper.clearSpace();
                getSv().getPlayer().getInv().showInventory();
                System.out.println(" 0.- Back to Inventory\n");
                System.out.print(" -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    getSv().getPlayer().getInv().showInventory();
                    System.out.println(" 0.- Back to Inventory\n");
                    System.out.print(" -> ");
                    scan.next();
                }
                value = scan.nextInt();

                switch (value){
                    default:
                        if (value>0 && value<=(getSv().getPlayer().getInv().getItemList().size())){
                            helper.clearSpace();
                            getSv().getPlayer().getInv().useItem(getSv().getPlayer(), getSv().getPlayer().getInv().getItemList().get(value-1));
                            System.out.println(" ");
                            helper.pressAnyKeyToContinue();
                        }else {
                            System.out.println("\n|=========================================|");
                            System.out.println("\n\t\t That item doesn´t exists.\n");
                            System.out.println("|=========================================|\n");
                            helper.pressAnyKeyToContinue();
                        }
                        break;
                    case 0:
                        break;

                }
            }

        }catch (Exception e){
            System.out.println("|=========================================|");
            System.out.println(e.getMessage());
            System.out.println("|=========================================|");
        }
    }

    // Menu de quipamiento. Muestra el equipamiento del player
    private void equipmentMenu(){

        int value = -1;

        while (value != 0){
            helper.clearSpace();
            System.out.println("\n|=============== Equipment ===============|");
            System.out.println("\n"+ getSv().getPlayer().getWeapon().toString());
            System.out.println(getSv().getPlayer().getArmor().toString());
            System.out.println("\n 0.- Back to Game Menu");
            System.out.println("\n|=========================================|");
            System.out.print("\n -> ");

            while (!scan.hasNextInt()){
                helper.clearSpace();
                System.out.println("\n|=============== Equipment ===============|");
                System.out.println("\n"+ getSv().getPlayer().getWeapon().toString());
                System.out.println(getSv().getPlayer().getArmor().toString());
                System.out.println("\n 0.- Back to Game Menu");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");
                scan.next();
            }

            value = scan.nextInt();

            switch (value){
                case 0:
                    break;
                default:break;
            }
        }
        helper.pressAnyKeyToContinue();
    }

    // Menu de Stats. Los puestra y da la opcion de agregar puntos.
    private void statsMenu(){

        int value = -1;

        while (value != 0) {
            helper.clearSpace();
            System.out.println("\n|============== Stats Menu ===============|\n");
            System.out.println(getSv().getPlayer().getStats().toString()+"\n");
            System.out.println(" Points: "+ getSv().getPlayer().getLevelSystem().getLevelPoint()+"\n");
            System.out.println(" 1.- Add STR");
            System.out.println(" 2.- Add DEF");
            System.out.println(" 3.- Add STA");
            System.out.println("\n 0.- Back to Game Menu");
            System.out.println("\n|=========================================|");
            System.out.print("\n -> ");

            while (!scan.hasNextInt()) {
                helper.clearSpace();
                System.out.println("\n|============== Stats Menu ===============|\n");
                System.out.println(getSv().getPlayer().getStats().toString()+"\n");
                System.out.println(" Points: "+ getSv().getPlayer().getLevelSystem().getLevelPoint()+"\n");
                System.out.println(" 1.- Add STR");
                System.out.println(" 2.- Add DEF");
                System.out.println(" 3.- Add STA");
                System.out.println("\n 0.- Back to Game Menu");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");
                scan.next();
            }

            value = scan.nextInt();

            switch (value) {
                case 1:
                    addStrMenu();
                    break;
                case 2:
                    addDefMenu();
                    break;
                case 3:
                    addStaMenu();
                    break;
                case 0:
                    break;
                default:
                    break;


            }
        }
    }

    // Menu para agregar STR Points
    private void addStrMenu(){
        System.out.println(" Add Point: ");
        while (!scan.hasNextInt()){
            System.out.println(" Add Point: ");
            scan.next();
        }
        int addPoint = scan.nextInt();

        if (addPoint > getSv().getPlayer().getLevelSystem().getLevelPoint() || addPoint<0 ){
            System.out.println("\n\tYou do not have enough points.\n");
            helper.pressAnyKeyToContinue();
        }else {
            getSv().getPlayer().getStats().addStr(addPoint);
            getSv().getPlayer().getLevelSystem().applyPoints(addPoint);
        }
    }

    // Menu para agregar DEF Points
    private void addDefMenu(){
        System.out.println(" Add Point: ");
        while (!scan.hasNextInt()){
            System.out.println(" Add Point: ");
            scan.next();
        }
        int addPoint = scan.nextInt();

        if (addPoint > getSv().getPlayer().getLevelSystem().getLevelPoint() || addPoint<0){
            System.out.println("\n\tYou do not have enough points.\n");
            helper.pressAnyKeyToContinue();
        }else {
            getSv().getPlayer().getStats().addDef(addPoint);
            getSv().getPlayer().getLevelSystem().applyPoints(addPoint);
        }
    }

    // Menu para agregar STA Points
    private void addStaMenu(){
        System.out.println(" Add Point: ");
        while (!scan.hasNextInt()){
            System.out.println(" Add Point: ");
            scan.next();
        }
        int addPoint = scan.nextInt();

        if (addPoint > getSv().getPlayer().getLevelSystem().getLevelPoint() || addPoint<0){
            System.out.println("\n\tYou do not have enough points.\n");
            helper.pressAnyKeyToContinue();
        }else {
            getSv().getPlayer().getStats().addSta(addPoint);
            getSv().getPlayer().getLevelSystem().applyPoints(addPoint);
        }
    }

    // Menu de manejo de mapas
    private void mapMenu(){
        try {

            helper.clearSpace();
            System.out.println("\n|=============== Map Menu ================|");
            getSv().showMapList();  // Lista de mapas. Excepcion controlada
            System.out.println("\n "+ getSv().getMapList().get(getIndexOfVentormenta()).getTier()+".- Ventormenta | "+ getSv().getMapList().get(getIndexOfVentormenta()).getMoveCost()+" Gold.");
            System.out.println(" "+ getSv().getMapList().get(getIndexOfTarkan()).getTier()+".- Tarkan | "+ getSv().getMapList().get(getIndexOfTarkan()).getMoveCost()+" Gold.");
            System.out.println(" "+ getSv().getMapList().get(getIndexOfAzeroth()).getTier()+".- Azeroth | "+ getSv().getMapList().get(getIndexOfAzeroth()).getMoveCost()+" Gold.");
            System.out.println("\n 0.- Back to Game Menu");
            System.out.println("\n|=========================================|");
            System.out.print("\n -> ");

            while (!scan.hasNextInt()) {
                helper.clearSpace();
                System.out.println("\n|=============== Map Menu ================|");
                getSv().showMapList();  // Lista de mapas. Excepcion controlada
                System.out.println("\n "+ getSv().getMapList().get(getIndexOfVentormenta()).getTier()+".- Ventormenta | "+ getSv().getMapList().get(getIndexOfVentormenta()).getMoveCost()+" Gold.");
                System.out.println(" "+ getSv().getMapList().get(getIndexOfTarkan()).getTier()+".- Tarkan | "+ getSv().getMapList().get(getIndexOfTarkan()).getMoveCost()+" Gold.");
                System.out.println(" "+ getSv().getMapList().get(getIndexOfAzeroth()).getTier()+".- Azeroth | "+ getSv().getMapList().get(getIndexOfAzeroth()).getMoveCost()+" Gold.");
                System.out.println("\n 0.- Back to Game Menu");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");
                scan.next();
            }

            int value = scan.nextInt();

            if (value!=0){
                System.out.println("\n|=========================================|");
                getSv().moveTo(getSv().getMap(value));
                helper.printWithSleep("\n\t\t Moving to "+ getSv().getMap(value));

            }
            //Cambia de mapa. Excepcion controlada:
            //moveTo valida que no quiera ir al mapa en el que se encuentra
            //getMap valida si el mapa existe (que value sea una opt correcta)

        }catch (Exception e){
            System.out.println("\n|=========================================|");
            System.out.println(e.getMessage());
            System.out.println("|=========================================|\n");
            helper.pressAnyKeyToContinue();
        }
    }

    /** Extras **/

    public int getIndexOfVentormenta(){
        return getSv().getMapList().indexOf(new Ventormenta());
    }

    public int getIndexOfTarkan(){
        return getSv().getMapList().indexOf(new Tarkan());
    }

    public int getIndexOfAzeroth(){
        return getSv().getMapList().indexOf(new Azeroth());
    }

    /** Getters & Setters **/

    public BattleGame getSv() {
        return sv;
    }

    private void setSv(BattleGame sv) {
        this.sv = sv;
    }
}
