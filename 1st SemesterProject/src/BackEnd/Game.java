package BackEnd;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import BackEnd.WorldFill.Room;
import BackEnd.WorldFill.NPC;
import BackEnd.WorldFill.HostileNPC;
import BackEnd.WorldFill.Beverage;
import BackEnd.WorldFill.Item;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class Game {

    boolean wantToQuit = false;
    private PC player;
    private World world;
    private HiScoreManager manager = new HiScoreManager();
    private HostileNPC enemy;
    private String endMessage;
    
/**
 * Constructor makes a new player and world.
 */
    Game() {

        player = new PC();
        world = new World();
        player.move(world.getRoom("Bar"));

    }
    /**
     * Sets all the parameters of player and world to match that of the gamestate.
     * @param gameState The game state the was saved.
     */
    void setGameState(GameState gameState){
        
        player.setPC(gameState.getPlayer());
        world.setWorld(gameState.getWorld());
    }
    
    /**
     * Checks if Hobos can move.
     * @return world.isHobosOnTheMove(), is the boolean in world that allows Hobos to move.
     */
    boolean getIsHobosOnTheMove(){
        return world.isHobosOnTheMove();
    }

    /**
     * Prints the welcome message and the current room
     */
    void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the life of detective Dindunuffin.");
        System.out.println("The commissioner wants you in the police department.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
        getInfo();
    }

    /**
     * Set the name of the player, used for highscore
     * @param string is the name you want the player to be called.
     */
    void setName(String string) {
        player.setName(string);
    }
    
    /**
     * Handles the inspect menu for UI
     * @return menus is the Set of the inventory and journal, and the the player can choose in UI. 
     */
    Set<String> inspectMenu() {
        System.out.println("Which menu do you wish to inspect?");
        Set<String> menus = new HashSet();
        menus.add("Inventory");
        menus.add("Journal");
        return menus;
    }

    /**
     * Checks if the player wants the inventory or the journal, and then checks if they are empty.
     * @param string is what the player chose in the UI "Journal" or "Inventory"
     * @return 
     */
    Set<String> inspect(String string) {
        timeLoop(1);
        
        if (string.equals("Inventory")) {
            if (player.getInventory().isEmpty()) {

                System.out.println("Your inventory is empty.");
            }
            return player.getInventory().keySet();
        } else {
            if (player.getJournal().isEmpty()) {
                System.out.println("Your journal is empty.");
            }
            return player.getJournal().keySet();
        }
    }
    
    /**
     * The Clue in Journal the player wants to inspect.
     * @param entry name of the clue the player wants to inspect
     */
    void journal(String entry) {
        player.inspectEntry(entry);
    }

    /**
     * The Item in Inventory the player wants to inspect.
     * @param entry name of the clue the player wants to inspect
     */
    void inventory(String item) {
        player.inspectItem(item);
    }

    /**
     * Creates the state of the game useing GameState.class.'
     * @return Is the gameState the player wants to saved, and it passed down
     * to save file to be written
     */
    Object save() {
        GameState gameState = new GameState(player, world);
         return gameState;
    }
   
    /**
     * Moves the player from room to room.
     *
     * @param e is the direction you want to move (north, west, south, east)
     */
    public boolean UIGo(String e) {
        timeLoop(2);
        Room nextRoom = player.getRoom().getExit(e);
        if(nextRoom==world.getRoom("Home")&& player.getDrunkenness()>=80){
        world.getHostileNPC("Wife").setAggression(1);
        }
        if (nextRoom == world.getRoom("Crime Scene")) {
            world.getNPC("Bartender Bert").fulfillCondition();
        }
        if (nextRoom == world.getRoom("Partner's Home")) {
            if (player.getInventory().containsKey("Key To Partner's Home")) {
                player.move(nextRoom);
                System.out.println(player.getRoom().getLongDescription());
                getInfo();
            } else {
                System.out.println("The door is locked. You need a key to enter here.");
            }
        } else {
            player.move(nextRoom);
            System.out.println(player.getRoom().getLongDescription());
            getInfo();
            enemy = getJumped();
            if (enemy != null) {
                System.out.println(enemy.getFightScream());
                System.out.println("You are now fighting " + enemy.getName() + ".");
                if (player.getInventory().containsKey("Gun")) {
                    player.setDamage(30);
                    System.out.println("You draw your gun.");
                }
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return Returns the fighter the player might fight. this method finds a
     * random number between 0 and 1, if the number is lower than HostileNPC
     * aggression, then you fight.
     */
    public HostileNPC getJumped() {
        for (String fighter : player.getRoom().getNPCsInRoomMap().keySet()) {
            if (world.getHostileNPC(fighter) != null) {
                if (Math.random() < (world.getHostileNPC(fighter).getAggression())) {
                    return world.getHostileNPC(fighter);
                }
            }
        }
        return null;
    }

    /**
     * Prints the help message out.
     */
    public void help(){
        System.out.println("Your job is to discover and solve the murder. During "
                + "your quest, you must avoid getting sober at all cost, by drinking "
                + "whatever drinks you can find.");
    }

    /**
     * Prints out the info of the about who is the in the room apart from the player.
     */
    private void getInfo() {
        if (player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("You are all alone." + "\n");
        } else {
            System.out.println("The other people here are:");
            String people = player.getRoom().getNPCsInRoomMap().keySet().toString();
            System.out.println(people.substring(1, (people.length() - 1)) + "\n");
        }
    }

    /**
     * The drink method adds drunkness, and removes the sips from the Beverage, 
     * and if it becomes empty, it gets thrown away.
     */
    public void drink() {
        timeLoop(1);
        for (Item drink : player.getInventory().values()) {
            if (drink instanceof Beverage) {
                System.out.println("You drink some " + ((Beverage) drink).getName() + ", you start to feel all your problems disappear");
                player.addDrunkenness(((Beverage) drink).getAlcoholContent());
                ((Beverage) drink).removeSip();
                if (((Beverage) drink).getNumberOfSips() <= 0) {
                    player.getInventory().remove(drink.getName());
                    System.out.println("You emptied your bottle and tossed it away.");
                }
                break;
            }
        }
    }

    /**
     * Sends the player and arrested NPC to Jail.
     * @param scum is the NPC that was Arrested.
     */
    public void goToJail(NPC scum) {
        System.out.println("You moved the scum to jail.");
        player.getRoom().moveNpc(scum, world.getRoom("Jail"));
        player.move(world.getRoom("Jail"));
        System.out.println("Commissioner: Good job, now you need to go find "
                + "some better evidence to convict this bastard. I will be in the Police department.");
        world.getRoom("Home").addItemToRoom(world.getItem("Badge"));
    }
    
    /**
     * Gets the TimeString from player, used for displaying the time on the HUD.
     * @return The timeSTring from player. Format example: "12:02"
     */
    public String getTimeString(){
    return player.getTimeString();
    }
 
    /**
     * Gets the Highscore of previous games and add it in, sorting it in the process.
     */
    public void win() {
        manager.retrieve();
        manager.addScore(player.getName(), player.getPoints());
        //todo make highscore application
    }

    /**
     * Gets the highscores from the manager as a list of lists.
     * @return the scores lists
     */
    public List<List<String>> getScores() {
        return manager.getScores();
    }

    /**
     * Adds damage on top of your and the enemy's base damage
     * @return 
     */
    private int damageRandomizer() {
        return ((int) (Math.random() * 11) - 5);
    }

    /**
     * Updates the crimescene so corroner disapperes with corpse, and makes it 
     * so Hobos become agressive and start moving around
     */
    private void updateCrimeScene() {
        world.getRoom("Crime Scene").removeNpcFromRoom(world.getNPC("Coroner"));
        world.getRoom("Crime Scene").addItemToRoom(world.getItem("Corpse Outline"));
        world.getRoom("Crime Scene").removeItemFromRoomMap("Corpse");

        for (String hobo : world.getRoom("Crime Scene").getNPCsInRoomMap().keySet()) {
            world.getHostileNPC(hobo).setAggression(0.3);
        }
        world.setHobosOnTheMove(true);
    }

    /**
     * Gets the player health in so it can be used as a percentage.
     * @return (player.getCurrentHealth() / 100.0);
     */
    double playerHealthPercent() {
        return (player.getCurrentHealth() / 100.0);
    }

    /**
     *  Gets the enemy health in so it can be used as a percentage.
     * @return (enemy.getHealth() / (enemy.getTotalHealth() * 1.0));
     */
    double enemyHealthPercent() {
        return (enemy.getHealth() / (enemy.getTotalHealth() * 1.0));
    }

    /**
     * Reset the damage in case you drop your gun afterwards.
     * and get who is in the room, and sets enemy to null.
     */
    void combatEnd() {
        player.setDamage(10);
        getInfo();
        enemy = null;
    }

    int fight() {
        timeLoop(1);

        int random1 = damageRandomizer();
        int random2 = damageRandomizer();
        System.out.println("You strike your opponent and deal " + (player.getDamage() + random1) + " damage.");
        enemy.setHealth(enemy.getHealth() - (player.getDamage() + random1));
        if (enemy.getHealth() <= 0) {
            System.out.println("You defeated you oppoent!");
            player.removePoints(15);
            player.getRoom().removeNpcFromRoom(enemy);
            if(world.getHobos().contains(enemy)){
            world.getHobos().remove(enemy);
            
            }
            combatEnd();
            return 2;
        } else {
            System.out.println("Your opponent retaliates.");
            System.out.println("Your take " + (enemy.getDamage() + random2) + " damage.");
            player.setCurrentHealth(player.getCurrentHealth() - (enemy.getDamage() + random2));
        }

        if (player.getCurrentHealth() <= 0) {
            endMessage = "You got killed by " + enemy.getName() + ".";
            return 0;
        }
        return 1;
    }

    /**
     * Send the name, damage + randomizer and health to the fightscreen in UI, as strings.
     * @return String Array with the enemy data.
     */
    String[] getEnemyData() {
        String[] enemyData = new String[3];
        enemyData[0] = enemy.getName();
        enemyData[1] = Integer.toString(enemy.getDamage()+5) + "-" + Integer.toString(enemy.getDamage()+11);
        enemyData[2] = Integer.toString(enemy.getHealth());
        return enemyData;
    }

    
    int run() {
        timeLoop(1);
        if (Math.random() < 0.7) {

            System.out.println("You ran away like a coward.");
            player.moveBack();
            System.out.println(player.getRoom().getLongDescription());
            combatEnd();
            return 2;
        } else {
            System.out.println("Your opponent didn't let you escape, and you got hit.");
            int random = damageRandomizer();
            System.out.println("Your take " + (enemy.getDamage() + random) + " damage.");
            player.setCurrentHealth(player.getCurrentHealth() - (enemy.getDamage() + random));
        }

        if (player.getCurrentHealth() <= 0) {
            endMessage = "You got killed by " + enemy.getName() + ".";
            return 0;
        }
        return 1;
    }

    int calm() {
        timeLoop(1);
        if (Math.random() < 0.1) {
            System.out.println("You managed to calm down your opponent.");
            enemy.calmDown();
            player.addPoints(10);
            combatEnd();
            return 2;
        } else {
            int random = damageRandomizer();
            player.setCurrentHealth(player.getCurrentHealth() - (enemy.getDamage() + random));
            System.out.println("You failed to calm your opponent, and got "
                    + "struck. you took " + (enemy.getDamage() + random) + " damage.");
        }
        if (player.getCurrentHealth() <= 0) {
            endMessage = "You got killed by " + enemy.getName() + ".";
            return 0;
        }
        return 1;
    }
    
    /**
     * Checks if the player is the PD so that it seems like he is talking to The Commisioner.
     * Then it gets the journal so it can gets
     * @return 
     */
    Set<String> convictMenu() {
        if (player.getRoom() == world.getRoom("Police Department")) {

            if (!player.getJournal().isEmpty()) {
                System.out.println("Which piece of evidence to you wish to convict with?");
                return player.getJournal().keySet();
            } else {
                System.out.println("You don't have any evidence.");
                return null;
            }
        } else {
            System.out.println("You are not near the commisioner, so you can't do this.");
            return null;
        }
    }

    /**
     * Checks if the clue is convictible or badge so it can be handled arcordingly.
     * Advances time 10 minutes
     * @param clue The clue the player wants to use.
     * @return 0 = Clue is not usable in court. 1 = Clue is good to use in court 2 = The clue is badge.
     */
    int convict(String clue) {
        timeLoop(10);
        if (!clue.equals("Badge")) {

            if (player.getJournal().get(clue).isConvictable()) {

                player.addToevidence(clue);
                System.out.println(clue + " has been added to Evidence list");
                if (player.isSecondEvidence()) {
                    convictWin();
                    win();
                    return 1;

                }
                return 0;
            } else {
                System.out.println(clue + " is not good enough to use in Court");
                return 0;
            }
        } else {
            System.out.println("Commisioner: Where did you find this? This evidence could be used to convict anyone."
                    + "\nDid you find this on the perpetrator?");
            return 2;
        }
    }
    
    /**
     * Takes the arrested NPC(s) in jail, to court and then convicts them of Murder.
     */
    private void convictWin() {
        String criminals = "";
        for (String npc : world.getRoom("Jail").getNPCsInRoomMap().keySet()) {
            criminals.concat((npc + (", ")));
        }
        String reverse = new StringBuffer(criminals).reverse().toString();
        reverse.replaceFirst(",", "");
        reverse.replaceFirst(",", " and");
        String criminalsDone = new StringBuffer(reverse).reverse().toString();
        endMessage = "Judge: We have found " + criminals + "guilty of murder.";
    }

    /**
     * This method is called when the player decides whether to tell the truth
     * or lie about the badge.
     *
     * @param answer The parameter is "Yes" or "No", where yes is lying and no
     * is confessing.
     * @return This function returns true if the player wins the game, and false
     * if the player continues the game.
     */
    boolean badgeResponse(String answer) {
        if (answer.equals("No")) {
            endMessage = "You told the truth and confessed to your crime.";
            player.addPoints(20);
            win();
            return true;
        } else {
            System.out.println("You lied");
            player.removePoints(40);
            player.addToevidence("Badge");
            System.out.println("Badge has been added to Evidence list");
            if (player.isSecondEvidence()) {
                System.out.print("Judge: We have found ");
                for (Entry<String, NPC> npc : world.getRoom("Jail").getNPCsInRoomMap().entrySet()) {
                    System.out.print(npc.getValue().getName() + (" "));
                }
                System.out.println("guilty of murder.");
                win();
                return true;
            }
            return false;
        }
    }

    /**
     * Sends the inventory to UI, so the player can selected what he wants to drop.
     * @return If the inventory isn't empty it returns the keyset of of the inventory. 
     * If it is empty it returns null and prints out a string.
     */
    Set<String> dropMenu() {
        if (!player.getInventory().isEmpty()) {
            System.out.println("These are the items in your inventory.");
            return player.getInventory().keySet();
        } else {
            System.out.println("Your inventory is empty.");
            return null;
        }
    }

    /**
     * drops an item, and 1 minute passes.
     * @param string is the name of the item you want dropped.
     */
    void drop(String string) {
        timeLoop(1);
        player.moveToRoom(world.getItem(string), player.getRoom());
    }

    /**
     * if there are NPCs in the room it Prints "Who do you wish to talk to?" and 
     * returns the Set of their names.
     * If there is no one it prints "You are all alone." and returns null
     * @return Set with the names / null
     */
    Set<String> talkMenu() {
        if (!player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("Who do you wish to talk to?");
            return player.getRoom().getNPCsInRoomMap().keySet();
        } else {
            System.out.println("You are all alone.");
            return null;
        }
    }

    /**
     * Uses the name of the NPC the player wants to talk to, and then gets their lines.
     * advances time 1 minute.
     * @param name is the name of the NPC the player wants to talk to.
     */
    void talk(String name) {
        NPC target = player.getRoom().getNPCsInRoomMap().get(name);
        target.getLine();
        if (target.getClue()) {
            player.addToJournal(target.giveClue());
        }
        timeLoop(1);
    }

    /**
     * if there are items in the room it Prints "You found these items.\nWhat 
     * do you want to look at?" and  returns the Set of the names.
     * If there is no one it prints "You are all alone." and returns null
     * @return Set with the names / null
     */
    Set<String> searchMenu() {
        if (!player.getRoom().getItemsInRoomMap().isEmpty()) {
            System.out.println("You found these items.\nWhat do you want to look at?");
            return player.getRoom().getItemsInRoomMap().keySet();
        } else {
            System.out.println("You can't find anything.");
            return null;
        }
    }

    /**
     * Uses the name of the Item the player wants to pick up to, and sees if it can be picled up.
     * advances time 1 minute. If it can be picked up it sets it as ItemToBePickedUp.
     * If not it gives a clue (if any)
     * Advances time 2 minutes
     * @param name is the name of the Item the player wants to pick up.
     */
    boolean search(String name) {
        timeLoop(2);
        Item item = world.getItem(name);
        System.out.println("\n" + item.getDescription() + "\n");
        if (item.getCollectible()) {
            System.out.println("Do you want to pick this item up? Yes/No");
            player.setItemToBePickedUp(item);
            return true;
        } else {
            System.out.println("This item can't be picked up.");
            if (item.getIsClue()) {
                player.addToJournal(item.giveClue());
                item.deactivateClue();
            }
            return false;
        }
    }
    
    /**
     * If the item can be picked up this asks if the player wants to pick it up 
     * the item in ItemToBePickedUp will be pickup. If it is a clue the clue is added to Journal.
     * Advances time 1 minute.
     * @param answer Yes/ No 
     */
    void pickup(String answer) {
        timeLoop(1);
        if (answer.equals("No")) {
            System.out.println("You decided not not to pick the item up.");
        } else {
            Item item = player.getItemToBePickedUp();
            
            if (item.getIsClue()) {
                player.addToJournal(item.giveClue());
            }
            System.out.println(player.addToInventory(item));
        }
    }

    /**
     * Prints out the end score if the player was a good or a bad cop.
     * @return 
     */
    String endScore() {
        if (player.getPoints() >= 100) {
            return "You were rated a " + (player.getPoints() - 100) + "% good cop.";
        } else {
            return "You were rated a " + (100 - player.getPoints()) + "% bad cop.";
        }
    }

    /**
     * If the player Loses, the end message will be why they lost.
     * @return The end message
     */
    String endMessage() {
        return endMessage;
    }

      /**
     * if there are NPCs in the room it Prints ""Who do you wish arrest?" and 
     * returns the Set of their names. No one is added so if the player doesn't want to 
     * arrest any one yet.
     * If there is no one it prints "There are no one here for you to arrest." and returns null
     * @return Set with the names of NPCs / empty Set
     */
    Set<String> arrestMenu() {
        if (!player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("Who do you wish arrest?");
            Set<String> victims = new HashSet(player.getRoom().getNPCsInRoomMap().keySet());
            victims.add("No one");
            return victims;
        } else {
            System.out.println("There are no one here for you to arrest.");
            return new HashSet();
        }
    }

    /**
     * Uses the name of the NPC the player wants to arrest to, and then arrests 
     * them if they don't have an alibi. And then it updates the Crimescene.
     * If the NPC has an alibi the player will loose.
     * advances time 10 minutes.
     * @param name is the name of the NPC the player wants to arrest.
     * @return True if the NPC can be arrested, false if the alibi is valid.
     */
    public boolean arrest(String name) {
        if (player.getRoom().getNPCsInRoomMap().get(name).getAlibi() != null) {
            endMessage = player.getRoom().getNPCsInRoomMap().get(name).getAlibi();
            return false;
        } else {
            world.getNPC("Commissioner Curt").fulfillCondition();
            goToJail(player.getRoom().getNPCsInRoomMap().get(name));
            if (!world.isHobosOnTheMove()) {
                updateCrimeScene();
            }
            timeLoop(10);
            return true;
        }
    }

    /**
     * This method returns an List with the player's points and drunkenness,
     * intended for updating the HeadsUpDisplay(HUD)
     *
     * @return An List with 3 integers, on index 0 is the player's drunkenness,
     * on index 1, the player's good cop points and on index 2 the player's bad
     * cop points. if the player is currently a good cop, bad cop will be null,
     * and vice versa.
     */
    List<Integer> getHUD() {
        List<Integer> list = new ArrayList();
        list.add(0, player.getDrunkenness());
        if (player.getPoints() < 100) {
            list.add(1, null);
            list.add(2, 100 - player.getPoints());
        } else {
            list.add(1, player.getPoints() - 100);
            list.add(2, null);
        }

        return list;
    }

    /**
     * Gets the Drunkness in percent.
     * @return drunkenness Format: "Drunkenness: " + percent + "%" 
     */
    String getDrunkenness() {
        String percent = String.format("%02d", player.getDrunkenness());
        String drunkenness = ("Drunkenness: " + percent + "%");
        return drunkenness;
    }

    HiScoreManager getHiScoreManager() {
        return manager;
    }

    /**
     * Gets the played so UI can see what room the player is in, and the exits of the room.
     * @return player
     */
    PC getPlayer() {
        return player;
    }

    /**
     * Moves the Hobos when HobosOnTheMove==true.
     * It gets all the Hobos, gets their exits, makes a list of the of the exits,
     * then it rolls for what exit the hobo should take. If it rolls 0 the hobo stays.
     */
    private void NpcMover() {
        for (HostileNPC hobo : world.getHobos()) {
            Room currentRoom = world.getRoom(hobo.getCurrentRoomName());
//            System.out.println(hobo.getName()+" Chosen in "+hobo.getCurrentRoomName());//test
            List<String> exits = new ArrayList<>();
            exits.add("Stay");
            for (Iterator it = currentRoom.getExit().keySet().iterator(); it.hasNext();) {
                String direction = (String) it.next();
                if (currentRoom.getExit(direction).isHoboAccessable()) {
                    exits.add(direction);
                }
            }

//            int i = 0;//test
//            for(String test : exits){//test
//                System.out.println(i + " " + test);//test
//                i++;//test
//                }//test
            int lenght = exits.size();
//            System.out.println(lenght + " lenght");//test
            int roll = (int) (Math.random() * (lenght - 0));
//            System.out.println("rolled "+ roll);//test
            if (!exits.get(roll).equals("Stay")) {
                currentRoom.moveNpc(hobo, currentRoom.getExit(exits.get(roll)));
//                System.out.println("Moved to "+ currentRoom.getExit(exits.get(roll)).getRoomName()+"\n");//test
            }
//            else{System.out.println("stay");} //test
        }
    }

    /**
     * Removes 1 drunkness, used in timeloop.
     */
    private void remover() {
        player.removeDrunkenness(1);
    }

    /**
     * Organizes time from just minutes to hours and minutes.
     * Controls messages to the player about Drunkenness
     * and moves Hobos useing time.
     */
    private void properTimer() {
        if (player.getMinutes() >= 60) {
            player.setHour(player.returnHours() + 1);
            player.setMinutes(0);
            if (player.getDrunkenness() >= 100) {
                endMessage = "You are completely smashed and pass out on the floor";
            }
            if (player.getDrunkenness() < 10) {
                System.out.println("You start to feel your hands again, if you dont drink soon you might die");
            }
            if (player.getDrunkenness() <= 0) {
                endMessage = "You feel completely sober, you fall down to the floor and die, knowing nobody loved you.";
            }
        }
        if (player.getMinutes() % 3 == 0 && world.isHobosOnTheMove() == true) {
            NpcMover();
        }
    }

    /**
     * Adds a minute, runs properTimer() and remover().
     * This method is used to control time and what it does to the player, and the world
     * @param runtimes ammount of minutes you want to add to time.
     */
    private void timeLoop(int runtimes) {
        int i;
        for (i = 0; i < runtimes; i++) {
            player.passTime(1);
            properTimer();
            remover();
        }
    }
}
