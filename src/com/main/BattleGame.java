package com.main;

import com.main.Characters.Character;   // La importa automaticamente de esta forma para no tener
import com.main.Characters.*;           // conflictos con Character, objeto contenedor de primitivos char
import com.main.Items.*;
import com.main.Maps.*;
import com.main.Npcs.*;

import java.util.ArrayList;
import java.util.List;

/**
 *              [ Clase "Main" del sistema ]
 *
 *  - > Tomada como una especie de "Server".
 *  - > start() inicia el programa.
 *
 *  - > Contiene los funcionamientos principales del sistema.
 *
 ***/

/**
 *                  [ Flujo de la batalla ]
 *
 *  - > Se llama a searchBattle() desde el Menu, la cual define
 *  contra que se enfrentara el usuario en la pelea de manera
 *  aleatoria.
 *
 *  enemyType define contra que enemigo se enfrentara:
 *
 *      - true: 80% Npc rate
 *      - false: 20% Character rate
 *
 *  - > Implementa battle(), pasando por parametro searchEnemyNpc()
 *  ó searchEnemyCharacter() segun indique enemyType, recibiendo
 *  el retorno de esta misma para decidir si buscar otro enemigo
 *  de manera aleatoria o no, a traves del while que condiciona la
 *  ejecucion mediante el boolean continueBattle de searchBattle().
 *
 *      - searchEnemyNpc(): retorna una copia del Npc seleccionado
 *      del ArrayList de Npc´s disponibles.
 *      - searEnemyCharacter(): retorna una copa del Character
 *      seleccionado del ArrayList de Personajes disponibles.
 *
 *  - > battle() contiene el menu de batalla. No se implemento este
 *  en SystemMenu para tener toda la ejecución en un mismo metodo.
 *
 *  - > battle() es la ultima decantación en la cual se realizan los
 *  intercambios de daño entre ambos oponentes, se valida si mueren,
 *  y en base a eso se ejecutan
 *
 ***/

public class BattleGame implements iHelper{

    private static final String version = "1.9";
    private SystemMenu menu;
    private List<Character> playerList;
    private List<Map> mapList;
    private Character player;
    private Map actualMap;


    /** Constructor **/
    public BattleGame(){
        try {
            setPlayerList(new ArrayList<>());
            setMapList(new ArrayList<>());
            setMapList_nofile();  // Setea en el array los mapas predeterminados (eliminar al usar archivos)
            setPlayerList_nofile(); // Setea 3 pj predeterminados en el array (eliminar al usar archivos)
            setActualMap(getMapList().get(getMapList().indexOf(getMap(1))));
            setPlayer(null);
            setMenu(new SystemMenu(this));
        }catch (Exception e){
            System.out.println("\n Loading server error: "+e.getMessage());
        }

    }

    public void start(){

        try {
            getMenu().startMenu();
        }catch (Exception e){
            System.out.println("\t\t\t-=> FATAL ERROR <=-");
            System.out.println(e.getMessage());
        }
    }       // Inicia el sistema

    /// METHODS

    /******************** BATTLE METHODS ********************/

    // Selecciona de manera random un Npc del mapa y lo retorna
    private Npc searchEnemyNpc() throws Exception{

        if (getPlayer() == null){
            throw new Exception("\n\t You do not have a selected character.\n\t     Something strange happened.\n");
        }

        int min = 0;
        int max = (getActualMap().getNpcList().size());
        int rand;
        do {
            rand = (int)Math.floor(Math.random()*(min-(max+1))+(max));
        }while (rand>getActualMap().getNpcList().size() || rand<0 );

        // rand: devuelve un numero aleatorio entre el max y el min, incluidos ambos

        if (rand<0 || rand>=getActualMap().getNpcList().size()){             // valida que a ultima instancia no ocurra
            throw new Exception("\n\t Searching random Npc error.");         // ningun error
        }
        Npc enemy = getActualMap().getNpcList().get(rand);

        if (enemy.getClass() == Dragon.class){                              // Retorna un nuevo Npc
            return new Dragon(getActualMap().getTier());                    // para no modificar los del array
        }else if(enemy.getClass() == Elf.class){
            return new Elf(getActualMap().getTier());
        }else if(enemy.getClass() == Warrior.class){
            return new Warrior(getActualMap().getTier());
        }else {
            throw new Exception("\n\t Searching random Npc error.");
        }
    }

    // Selecciona de manera random un Character del BattleGame y lo retorna
    private Character searchEnemyCharacter() throws Exception{

        if (getPlayer() == null){
            throw new Exception("\n\t You do not have a selected character.\n\t     Something strange happened.\n");
        }

        boolean check = false;
        int min = 0;
        int max = getPlayerList().size();
        int rand = -1;

        while (!check){
            do {
                rand = (int)Math.floor(Math.random()*(min-(max+1))+(max));  // rand: devuelve un numero aleatorio entre
                                                                            // entre el max y el min, incluidos ambos
            }while (rand>= getPlayerList().size() || rand<0 );              // valida que rand corresponda a un indice valido
            if(!getPlayer().equals(getPlayerList().get(rand))){             // valida que el indice que indica rand
                check = true;                                               // no sea el mismo del actualPlayer
            }else {
                rand = -1;                                                  // si el pj es igual al actual, vuelve a
            }                                                               // buscar uno aleatorio
        }

        if (rand<0 || rand>= getPlayerList().size()){                           // valida que a ultima instancia no ocurra
            throw new Exception("\n\t Searching random Player error.");         // ningun error
        }

        Character enemy = getPlayerList().get(rand);

        if (enemy.getClass() == Knight.class){                              // Retorna un nuevo Character
            return new Knight(getPlayerList().get(rand).getName());         // para no modificar los del array
        }else if(enemy.getClass() == Wizard.class){
            return new Wizard(getPlayerList().get(rand).getName());
        }else if(enemy.getClass() == Archer.class){
            return new Archer(getPlayerList().get(rand).getName());
        }else {
            throw new Exception("\n\t Searching random Player error.");
        }
    }

    // Ronda de ataques Character-Npc
    private void attackRoundVsNpc(Npc npc) throws Exception{
        if(getPlayer() == null || npc == null){
            throw new Exception("\n\t Entity Error (null)\n");
        }
        System.out.println(getPlayer().getName()+" >> "+(getPlayer().getWeapon().isCriticalRate() ? "¡"+getPlayer().attackNpc(npc)+"! >> "+npc.getName() : getPlayer().attackNpc(npc)+" >> "+npc.getName()));
        System.out.println(getPlayer().getName()+" << "+ npc.attackChar(getPlayer())+" << "+npc.getName());
    }

    // Ronda de ataques Character-Character
    private void attackRoundVsPlayer(Character player) throws Exception{
        if(getPlayer() == null || player == null){
            throw new Exception("\n\t Entity Error (null)\n");
        }
        System.out.println(getPlayer().getName()+" >> "+(getPlayer().getWeapon().isCriticalRate() ? "¡"+getPlayer().attackChar(player)+"! >> "+player.getName() : getPlayer().attackChar(player)+" >> "+player.getName()));
        System.out.println(getPlayer().getName()+" << "+(player.getWeapon().isCriticalRate() ? "¡"+player.attackChar(getPlayer())+"! << "+player.getName() : player.attackChar(getPlayer())+" << "+player.getName()));
    }

    // Batalla del jugador vs lo que venga por parametro ( Npc u otro Character )
    private boolean battle(Object enemyFound)throws Exception{

        Entity enemy;
        Item dropped =null;

        if (!(enemyFound instanceof Character) && !(enemyFound instanceof Npc)){
            throw new Exception("\n\t\t Parameter error\n");
        }else if (enemyFound instanceof Npc){
            enemy = (Npc) enemyFound;
            dropped = ((Npc)enemy).getItem();   //si es un npc asigna el posible drop de item
        }else {
            enemy = (Character) enemyFound;
        }

        boolean continueBattle = true;
        boolean firstEntrance = true;
        boolean searchAnother = false;

        int gold = enemy instanceof Npc ? ((Npc) enemy).getGold() : ((Character) enemy).getInv().getGold()/10;
        int exp = enemy instanceof Npc ? ((Npc) enemy).getExp() : ((Character) enemy).getLevelSystem().getExp()/10;
        int value = -1;

        while (value != 0 && continueBattle) {

            if (firstEntrance) {    // Valida primer ingreso al encontrarse con el oponente, da la opcion de pelear
                // contra el enemigo, curarse, buscar otro o volver a la zona segura
                helper.clearSpace();
                System.out.println("|============== Battle Zone ==============|");
                System.out.println("\n\t\t   -=[ Enemy Spotted ]=-\n");
                System.out.println(enemy.battleStats() + "\n");
                System.out.println(" 1.- Fight");
                System.out.println(" 2.- Use Health Potion");
                System.out.println(" 3.- Search another one");
                System.out.println("\n 0.- Go back to Safe Zone");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    System.out.println("|============== Battle Zone ==============|");
                    System.out.println("\n\t\t   -=[ Enemy Spotted ]=-\n");
                    System.out.println(enemy.battleStats() + "\n");
                    System.out.println(" 1.- Fight");
                    System.out.println(" 2.- Use Health Potion");
                    System.out.println(" 3.- Search another one");
                    System.out.println("\n 0.- Go back to Safe Zone");
                    System.out.println("\n|=========================================|");
                    System.out.print("\n -> ");
                    scan.next();
                }
                value = scan.nextInt();

            } else if (!enemy.isAlive()) {
                // Si no es el primer ingreso ( ya pelearon ) valida que lo
                // hayas matado

                helper.clearSpace();

                getPlayer().getDrop(gold, exp, dropped);     // Colecta el drop dejado por el enemigo muerto

                System.out.println("|============== Battle Zone ==============|");
                System.out.println("\n\t\t  You killed " + enemy.getName() + "\n");
                System.out.println(dropped == null ? "" : dropped.getName() + "obtainded.");
                System.out.println("  " + exp + " exp obtained.");
                System.out.println("  " + gold + " gold obtained.\n");
                System.out.println(" 1.- Search another enemy");
                System.out.println(" 2.- Go back to Safe Zone");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    System.out.println("|============== Battle Zone ==============|");
                    System.out.println("\n\t\t  You killed " + enemy.getName());
                    System.out.println("\n  " + exp + " exp obtained.");
                    System.out.println("  " + gold + " gold obtained.\n");
                    System.out.println(" 1.- Search another enemy");
                    System.out.println(" 2.- Go back to Safe Zone");
                    System.out.println("\n|=========================================|");
                    System.out.print("\n -> ");
                    scan.next();
                }
                value = scan.nextInt();

                switch (value) {    // en ambas asigno value = 0 para que corte la ejecucion del while general
                    case 1:
                        searchAnother = true; // Podria directamente hacer un return true para que corte
                        value = 3;                    // inmediatamente la ejecucuion del metodo y vuelva a ejecutarlo
                        break;                // buscando otro enemigo.
                    case 2:
                        continueBattle = false;
                        value = 0;
                        break;

                }

            }else if (getPlayer().isAlive()) {      // si ninguno de los dos murio, abre el menu de decision durante la batalla
                                                    // que permite seguir peleando, curarse, o escapar a la zona segura
                //helper.clearSpace();
                //helper.showBattleStats(getPlayer(), enemy);
                System.out.println(" 1.- Fight");
                System.out.println(" 2.- Use Health Potion");
                System.out.println("\n 0.- Go back to Safe Zone");
                System.out.println("\n|=========================================|");
                System.out.print("\n -> ");

                while (!scan.hasNextInt()) {
                    helper.clearSpace();
                    helper.showBattleStats(getPlayer(), enemy);
                    System.out.println(" 1.- Fight");
                    System.out.println(" 2.- Use Health Potion");
                    System.out.println("\n 0.- Go back to Safe Zone");
                    System.out.println("\n|=========================================|");
                    System.out.print("\n -> ");
                    scan.next();
                }
                value = scan.nextInt();
            }



            switch (value) {
                case 1:                                             // Opcion de pelear

                    firstEntrance = false;

                    helper.clearSpace();

                    if (enemyFound instanceof Npc) {

                        attackRoundVsNpc((Npc) enemy);

                        if (((Npc) enemy).getSta() <= 0) {      // si muere el contrincante lo setea como muerto
                            enemy.die();
                        }

                        helper.showBattleStats(getPlayer(), enemy);

                    } else {

                        attackRoundVsPlayer((Character) enemy);

                        if (((Character) enemy).getStats().getHealth() <= 0) { //Si muere el enemigo lo setea como muerto
                            enemy.die();
                        }
                        helper.showBattleStats(getPlayer(), enemy);
                    }

                    if (getPlayer().getStats().getHealth() <= 0) {     // Valida que el jugador actual no haya muerto

                        getPlayer().die();                          // muere
                        System.out.println("|=========================================|");
                        System.out.println("\n\t\t You´re dead");   // corta la batalla y retorna false anulando la opcion de
                        continueBattle = false;                       // buscar otro enemigo
                        helper.printWithRandonSleep("\n\t\tRespawning");
                        getPlayer().respawn();
                    }
                    break;
                case 2:                                             // Opcion de curarse
                    getPlayer().getInv().useItem(getPlayer(), new HealthPotion());
                    helper.pressAnyKeyToContinue();
                    break;
                case 3:                                                     // Opcion de buscar otro. Corta la batalla actual y
                    helper.clearSpace();
                    System.out.println("\n|=========================================|");
                    helper.printWithRandonSleep("\n\t\t\t Searching enemy");    // retorna true para que se finalice la ejecucion
                    searchAnother = true;                                   // de este metodo, y vuelva a ejecutarse. Podria volver
                    value = 0;                                              // a llamar a battleVsNpc y pasarle searchEnemyNpc
                    break;                                                  // pero entraria en una recursividad que me dejaria
                case 0:                                                     // este metodo en ejecucion la cantidad de veces que el
                    helper.clearSpace();
                    System.out.println("\n|=========================================|");
                    helper.printWithSleep("\n\t\t Runing to Safe Zone"); // usuario quiera buscar otro oponente.
                    continueBattle = false;
            }

        }



        return searchAnother;
    }

    // Busca un enemigo: 20% de que sea otro Character| 80% de que sea un Npc
    protected void searchBattle() throws Exception{

        boolean enemyType;
        boolean continueBattle = true;

        while (continueBattle){

            enemyType = Math.random()<0.8; // true = npc | false = character

            if (enemyType){
                continueBattle = battle(searchEnemyNpc());
            }else {
                continueBattle = battle(searchEnemyCharacter());
            }
        }
    }

    /********************************************************/

    /** Methods **/

    // Busca un pj y lo devuelve, si no lo encuentra lanza Excepcion
    public Character getPlayerByName(String name) throws Exception{

        Character searched = null;

        for (Character c : getPlayerList()){
            if (c.getName().equals(name))
                searched = c;
        }

        if (searched == null){      // Si no encuentra al pj lanza la excepcion
            throw new Exception("\n\t Character not found! Try again\n");
        }

        return searched;
    }

    // Busca un mapa por tier y lo devuelve, si no lo encuentra lanza excepcion
    public Map getMap(int tier) throws Exception{

        Map mp = null;

        for (Map m: getMapList()){
            if (m.getTier() == tier){
                mp = m;
            }
        }

        if (mp == null){        // Si no encuentra el mapa lanza la excepcion
            throw new Exception("\n\t\t  That map does not exist!\n");
        }

        return mp;
    }

    // Agrega un pj al array y lo asigna como actual. Si ya existe lanza Excepcion
    public void addPlayer(Character player) throws Exception{
        if (!playerExist(player.getName())){
            getPlayerList().add(player);
            setPlayer(player);
        }else {
            throw new Exception("\n\t Name is already used! Try again\n");
        }
    }

    // Crea un pj y lo devuelve. Si el tipo de clase es incorrecto lanza Excepcion -> Utilizado en la validacion
    public Character newPlayer(String name, int type) throws Exception{         //    al crear el pj

        Character pj;

        switch (type){
            case 1:
                pj = new Knight(name);
                break;
            case 2:
                pj = new Wizard(name);
                break;
            case 3:
                pj = new Archer(name);
                break;

                default: throw new Exception("\n\t\t  Wrong class! Try again\n");
        }

        return pj;
    }

    // Cambia de mapa. Valida que el mapa no sea el actual mediante Exception
    public void moveTo(Map map) throws Exception{

        if ( !getActualMap().equals(map) ){
                getPlayer().getInv().extractGold(map.getMoveCost());   // extractGold valida el oro suficiente mediante Exception
                setActualMap(map);
        }else {
            throw new Exception("\n\t You're already in"+map.toString()+"\n");
        }
    }

    // Listado de personajes. Lanza excepcion si se encuentra vacio el ArrayList o si es null
    public void showPlayerList() throws Exception{

        if (getPlayerList().isEmpty() || getPlayerList() == null){
            throw new Exception("\n\t No characters founded\n");
        }

        for (Character c : getPlayerList()){
            System.out.println(c.mainInfo());
        }
    }

    // Listado de Mapas. Lanza excepcion si se encuentra vacio el ArrayList o si es null
    public void showMapList() throws Exception{

        if (getPlayerList().isEmpty() || getPlayerList() == null){
            throw new Exception("\n\t No maps founded\n");
        }

        for (Map m : getMapList()){
            System.out.println("\n"+m.toString());
            m.showNpcList();
        }
    }

    /** Getters & Setters **/

    public SystemMenu getMenu() {
        return menu;
    }

    public void setMenu(SystemMenu menu) {
        this.menu = menu;
    }

    public String getVersion() {
        return version;
    }

    public Character getPlayer() {
        return player;
    }

    public void setPlayer(Character player) {
        this.player = player;
    }

    public List<Character> getPlayerList() {
        return playerList;
    }

    private void setPlayerList(List<Character> playerList) {
        this.playerList = playerList;
    }

    public Map getActualMap() {
        return actualMap;
    }

    public void setActualMap(Map actualMap) {
        this.actualMap = actualMap;
    }

    public List<Map> getMapList() {
        return mapList;
    }

    public void setMapList(List<Map> mapList) {
        this.mapList = mapList;
    }

    /** EXTRAS **/

    // agrega mapas predeterminados al array(cambiar al aplicar archivos)
    private void setMapList_nofile() {
        getMapList().add(0,new Ventormenta());
        getMapList().add(1,new Tarkan());
        getMapList().add(2,new Azeroth());
    }

    // agrega pj predeterminados al array (cambiar al aplicar archivos)
    private void setPlayerList_nofile(){
        try {
            addPlayer(new Knight("Riquelme"));
            getPlayer().getLevelSystem().addExp(29000);
            getPlayer().getInv().addGold(1000);
            getPlayer().getInv().addItem(new CriticalGem());
            getPlayer().getInv().addItem(new DefenseGem());
            getPlayer().getInv().addItem(new DamageGem());

            addPlayer(new Wizard("Merlin"));
            getPlayer().getLevelSystem().addExp(1500);
            getPlayer().getInv().addGold(10);
            getPlayer().getInv().addItem(new CriticalGem());
            getPlayer().getInv().addItem(new DefenseGem());
            getPlayer().getInv().addItem(new DamageGem());

            addPlayer(new Archer("TiraFlecha"));
            getPlayer().getLevelSystem().addExp(20000);
            getPlayer().getInv().addGold(1700);
            getPlayer().getInv().addItem(new CriticalGem());
            getPlayer().getInv().addItem(new DefenseGem());
            getPlayer().getInv().addItem(new DamageGem());


        }catch (Exception e){
            System.out.println("\n\t Data base error (Load characters)\n");
        }
    }

    // retorna true si el pj existe
    public boolean playerExist(String name){

        for (Character c : getPlayerList()){
            if (c.getName().equals(name)){
                return true;
            }
        }
        return false;
    }



    @Override
    public String toString(){
        String msgFmt = " Actual character:\n %s\n\n Total players: %d ";
        return String.format(msgFmt, getPlayer()==null ? "\n\t  < None >" : getPlayer().mainInfo(), getPlayerList().size());
    }
}
