package BackEnd;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import Acquaintance.IFoundation;
import BackEnd.WorldFill.Room;
import BackEnd.WorldFill.Hobo;
import BackEnd.WorldFill.NPC;
import BackEnd.WorldFill.HostileNPC;
import BackEnd.WorldFill.Beverage;
import BackEnd.WorldFill.Item;
import BackEnd.Command.Parser;
import BackEnd.Command.CommandWord;
import BackEnd.Command.Command;
import Foundation.SaveFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private Parser parser;
    boolean wantToQuit = false;
    private PC player;
    private World world = new World();
    private HiScoreManager manager = new HiScoreManager();
    private String temp;//todo make better
    private boolean hobosOnTheMove = false;
    private HostileNPC enemy;
    // Constructor calls createRooms and creates new Parser

    public Game() {

        parser = new Parser();
        player = new PC();
        player.move(world.getRoom("Bar"));
        
    }

    // Keeps game running requesting new command and ends the game
    // when processCommand returns true
    public void play() {
        System.out.println("testing? Y/N");
        Scanner testing = new Scanner(System.in);
        String tester = testing.nextLine().toLowerCase();
        if (tester.equals("y")) {
            tester();
        }
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            System.out.println("");

        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    // Prints the welcome message and the current room
    void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the life of detective Dindunuffin.");
        System.out.println("The commissioner wants you in the police department.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
        getInfo();
    }

    void setName(String string) {
        player.setName(string);
    }

    // Excecutes commands
    private boolean processCommand(Command command) {

        CommandWord commandWord = command.getCommandWord();

        if (commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        } else if (commandWord == CommandWord.GO) {
            //goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.SEARCH) {
            search("");
        } else if (commandWord == CommandWord.TALK) {
            talk("");
        } else if (commandWord == CommandWord.ARREST) {
            arrest("");
        } else if (commandWord == CommandWord.INSPECT) {
            //inspect(command);
        } else if (commandWord == CommandWord.DROP) {
            //drop(command);
        } else if (commandWord == CommandWord.LIE) {
            System.out.println("Lying is bad, and you should feel bad.");
        } else if (commandWord == CommandWord.CONVICT) {
            convict("");
        } else if (commandWord == CommandWord.DRINK) {
            drink();
        } else if (commandWord == CommandWord.DRUNKNESS) {
            drunkness();
        }
        return wantToQuit;
    }

    // Calls parser to show all possible commands
    private void printHelp() {

        System.out.println("Your command words are:");
        parser.showCommands();
    }

    Set<String> inspectMenu() {
        System.out.println("Which menu do you wish to inspect?");
        Set<String> menus = new HashSet();
        menus.add("Inventory");
        menus.add("Journal");
        return menus;
    }

    Set<String> inspect(String string) {
        timeloop(1);

        Set<String> test = new HashSet();
        if (string.equals("Inventory")) {
            if (player.displayInventoryMap().isEmpty()) {

                System.out.println("Your inventory is empty.");
            }
            return player.displayInventoryMap();
        } else {
            if (player.displayJournal().isEmpty()) {
                System.out.println("Your journal is empty.");
            }
            return player.displayJournal();
        }
    }

    void journal(String entry) {
        player.inspectEntryMap(entry);
    }

    void inventory(String item) {
        player.inspectItemMap(item);
    }

    void save() {
//        GameState gamestate = new GameState(player, world);
//        IFoundation.saveFile(gamestate);
//        System.out.println("Saving");
    }

    void load() {
        //todo
    }

    //Checks if directions has an exit and moves to next room
//    private void goRoom(Command command) {
//        if (!command.hasSecondWord()) {
//            System.out.println("Go where?");
//            return;
//        }
//
//        String direction = command.getSecondWord();
//
//        Room nextRoom = player.getRoom().getExit(direction);
//        timeloop(2);
//        if (nextRoom == null) {
//            System.out.println("There is no door!");
//        } else {
//            if (nextRoom == world.getRoom("Partner's Home")) {
//                if (player.getInventoryMap().containsKey("Key To Partner's Home")) {
//                    player.move(nextRoom);
//                    System.out.println(player.getRoom().getLongDescription());
//                    getInfo();
//                } else {
//                    System.out.println("The door is locked. You need a key to enter here.");
//                }
//            } else {
//                
//                player.moveBack();
//                player.move(nextRoom);
//
//                System.out.println(player.getRoom().getLongDescription());
//                getInfo();
//                HostileNPC enemy =getJumped();
//                if (enemy != null) {
//                    fightLoop(enemy);
//                }
//            }
//        }
//        
//    }
    /**
     * Moves the player from room to room.
     *
     * @param e is the direction you want to move (north, west, south, east)
     */
    public boolean UIGo(String e) {
        timeloop(2);
        Room nextRoom = player.getRoom().getExit(e);
        if(nextRoom==world.getRoom("Crime Scene")){
        world.getNPC("Bartender Bert").fulfillCondition();
        }
        if (nextRoom == world.getRoom("Partner's Home")) {

            if (player.getInventoryMap().containsKey("Key To Partner's Home")) {
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

                if (player.inventoryContains("Gun")) {
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

    // Quits the game
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;
        }
    }

    private void getInfo() {

        if (player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("You are all alone." + "\n");
        } else {
            System.out.println("The other people here are:");
            System.out.println(player.getRoom().getNPCsInRoomMap().keySet() + "\n");
        }
    }

    public void drink() {
        timeloop(1);
        for (Item drink : player.getInventoryMap().values()) {
            if (drink instanceof Beverage) {
                System.out.println("You drink some " + ((Beverage) drink).getName() + ", you start to feel all your problems disappear");
                player.addDrunkenness(((Beverage) drink).getAlcoholContent());
                ((Beverage) drink).removeSip();
                if (((Beverage) drink).getNumberOfSips() <= 0) {
                    player.getInventoryMap().remove(drink.getName());
                    System.out.println("You emptied your bottle and tossed it away.");
                }
                break;
            }
        }
    }

    public void goToJail(NPC scum) {
        System.out.println("You moved the scum to jail.");
        player.getRoom().moveNpc(scum, world.getRoom("Jail"));
        player.move(world.getRoom("Jail"));
        System.out.println("Commissioner: Good job, now you need to go find "
                + "some better evidence to convict this bastard. I will be in the Police department.");
        world.getRoom("Home").addItemToRoom(world.getItem("Badge"));
        parser.addFinishers();

    }
    
    public String getTime(){
    return player.getTime();
    }

    public void lose() {

    }

    public void win() {
        manager.retrieve();
        manager.addScore(player.getName(), player.getPoints());
        //todo make highscore application
    }

    public List<List<String>> getScores() {
        return manager.getScores();

    }

    public void drunkness() {
        System.out.println(player.getDrunkenness());
    }

    private int damageRandomizer() {
        return ((int) (Math.random() * 11) - 5);
    }

    private void updateCrimeScene() {
        world.getRoom("Crime Scene").removeNpcFromRoom(world.getNPC("Coroner"));
        world.getRoom("Crime Scene").addItemToRoom(world.getItem("Corpse Outline"));
        world.getRoom("Crime Scene").removeItemFromRoomMap("Corpse");

        for (String hobo : world.getRoom("Crime Scene").getNPCsInRoomMap().keySet()) {
            world.getHostileNPC(hobo).setAggression(0.3);
        }
        hobosOnTheMove = true;
    }
    
    double playerHealthPercent(){
        return (player.getCurrentHealth()/100.0);
    }
 
    double enemyHealthPercent(){
        return (enemy.getHealth()/(enemy.getTotalHealth()*1.0));
    }

    void combatEnd() {
        player.setDamage(10);
        getInfo();
        enemy=null;
    }

    int fight() {
        timeloop(1);
        
                int random1 = damageRandomizer();
                int random2 = damageRandomizer();
                System.out.println("You strike your opponent and deal " + (player.getDamage() + random1) + " damage.");
                enemy.setHealth(enemy.getHealth()-(player.getDamage() + random1));
                if (enemy.getHealth() <= 0) {
                    System.out.println("You defeated you oppoent!");
                    player.removePoints(15);
                    player.getRoom().removeNpcFromRoom(enemy);
                    combatEnd();
                    return 2;
                }else {
                    System.out.println("Your opponent retaliates.");
                    System.out.println("Your take " + (enemy.getDamage() + random2) + " damage.");
                player.setCurrentHealth(player.getCurrentHealth()-(enemy.getDamage() + random2));
                }
                
        
        

        if (player.getCurrentHealth() <= 0) {
            System.out.println("You died.");
            lose();
            return 0;
        }
        return 1;
    }

    	  public String[] getEnemyData(){
     String[] enemyData = new String[3];
        enemyData[0]=enemy.getName();
        enemyData[1]=Integer.toString(enemy.getDamage());
        enemyData[2]=Integer.toString(enemy.getHealth());
        return enemyData;
    }
    
    int run() {
        timeloop(1);
        
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
                player.setCurrentHealth(player.getCurrentHealth()-(enemy.getDamage() + random));
                }

        if (player.getCurrentHealth() <= 0) {
            System.out.println("You died.");
            lose();
            return 0;
        }
        return 1;
    }

    int calm() {
        timeloop(1);
                        if (Math.random() < 0.1) {
                    System.out.println("You managed to calm down your opponent.");
                    enemy.calmDown();
                    player.addPoints(10);
                    combatEnd();
                    return 2;
                } else {
                    int random = damageRandomizer();
                    player.setCurrentHealth(player.getCurrentHealth()-(enemy.getDamage() + random));
                    System.out.println("You failed to calm your opponent, and got "
                            + "struck. you took " + (enemy.getDamage() + random) + " damage.");
                }

        if (player.getCurrentHealth() <= 0) {
            System.out.println("You died.");
            lose();
            return 0;
        }
        return 1;
    }

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

    public int convict(String clue) {
        timeloop(10);
        if (!clue.equals("Badge")) {

            if (player.getJournal().get(clue).isConvictable()) {

                player.addToevidence(clue);
                System.out.println(clue + " has been added to Evidence list");
                if (player.isEvidence2()) {
                    System.out.print("Judge: We have found ");
                    for (Entry<String, NPC> npc : world.getRoom("Jail").getNPCsInRoomMap().entrySet()) {
                        System.out.print(npc.getValue().getName() + (" "));
                    }
                    System.out.println("guilty of murder.");

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
            System.out.println("You told the truth and confessed to your crime.");
            player.addPoints(20);
            win();
            return true;
        } else {
            System.out.println("You lied");
            player.removePoints(40);
            player.addToevidence("Badge");
            System.out.println("Badge has been added to Evidence list");
            if (player.isEvidence2()) {
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

    Set<String> dropMenu() {
        if (!player.getInventoryMap().isEmpty()) {
            System.out.println("These are the items in your inventory.");
            return player.getInventoryMap().keySet();
        } else {
            System.out.println("Your inventory is empty.");
            return null;
        }
    }

    void drop(String string) {
        timeloop(1);
        player.moveToRoom(world.getItem(string), player.getRoom());
    }

    Set<String> talkMenu() {
        remover();
        if (!player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("Who do you wish to talk to?");
            return player.getRoom().getNPCsInRoomMap().keySet();
        } else {
            System.out.println("You are all alone.");
            return null;
        }
    }

    void talk(String name) {
        timeloop(1);
        //Gives the player a list of NPCs in the room
        NPC target = player.getRoom().getNPCsInRoomMap().get(name);
        target.getLine();
        if (/*target.getClueCount() == target.getClueRelease()*/target.getClue()) {
            player.addToJournal(target.giveClue());
        }

    }

    Set<String> searchMenu() {
        remover();
        if (!player.getRoom().getItemsInRoomMap().isEmpty()) {
            System.out.println("You found these items.\nWhat do you want to look at?");
            return player.getRoom().getItemsInRoomMap().keySet();
        } else {
            System.out.println("You can't find anything.");
            return null;
        }

    }

    boolean search(String name) {
        timeloop(2);
        Item item = world.getItem(name);
        System.out.println("\n" + item.getDescription() + "\n");

        if (item.getCollectible()) {
            System.out.println("Do you want to pick this item up? Yes/No");
            temp = name;
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

    void pickup(String answer) {
        timeloop(1);
        if (answer.equals("No")) {
            System.out.println("You decided not not to pick the item up.");
        } else {
            Item item = world.getItem(temp);
            if (item.getIsClue()) {
                player.addToJournal(item.giveClue());
            }
            System.out.println(player.addToInventory(item, player.getRoom()));
        }
        temp = null;
    }

    String endScore() {
        if (player.getPoints() >= 100) {
            return "You were rated a " + (player.getPoints() - 100) + "% good cop.";
        } else {
            return "You were rated a " + (100 - player.getPoints()) + "% bad cop.";
        }

    }

    Set<String> arrestMenu() {
        if (!player.getRoom().getNPCsInRoomMap().isEmpty()) {
            System.out.println("Who do you wish arrest?");
            Set<String> victims = new HashSet(player.getRoom().getNPCsInRoomMap().keySet());
            victims.add("No one");
            return victims;
        } else {
            System.out.println("There are no one here for you to arrest.");
            return null;
        }
    }

    public boolean arrest(String name) {
        timeloop(10);

        if (player.getRoom().getNPCsInRoomMap().get(name).getAlibi()) {
            lose();
            return false;
        } else {
            world.getNPC("Commissioner Curt").fulfillCondition();
            goToJail(player.getRoom().getNPCsInRoomMap().get(name));
            updateCrimeScene();
            return true;
        }
    }

//        System.out.println("You have decided to begin arresting people, god bless you");
//        System.out.println("Are you sure?   Yes/No");
//        Scanner accusing = new Scanner(System.in);
//        String victim = accusing.nextLine().toLowerCase();
//        if (victim.equalsIgnoreCase("no")) {
//            System.out.println("You decided not to accuse anyone... for now");
//        } else if (victim.equalsIgnoreCase("yes")) {
//            System.out.println("These are the people you can accuse:");
//            System.out.println(player.getRoom().getNPCsInRoomMap().keySet());
//            Scanner choose = new Scanner(System.in);
//            String person = choose.nextLine();
//            boolean success = false;
//            if(currentRoom.getNPCsInRoomMap().containsKey(person)){
//            if (currentRoom.getNPCsInRoomMap().get(person).getAlibi()) {
//                        lose();
//                    } else {
//                        goToJail(currentRoom.getNPCsInRoomMap().get(person));
//                        updateCrimeScene();
//                    }
//            }else{
//                System.out.println("That person isn't here");
//            }
//            for (Entry<String,NPC> npc : player.getRoom().getNPCsInRoomMap().entrySet()) {
//                if (person.equals(npc.getValue().getName().toLowerCase())) {
//                    if (npc.getValue().getAlibi()) {
//                        lose();
//                        success = true;
//                        break;
//                    } else {
//                        goToJail(npc.getValue());
//                        updateCrimeScene();
//                    }
//                    success = true;
//                    break;
//                }
//
//            }
//            if (!success) {
//                System.out.println("If you're not going to be serious about this, find another job.");
//            }
//        } else {
//            System.out.println("Can't you just answer a simple Yes/No question?");
//        }
    public void tester() {
        System.out.println("write the method or room you would like to test");

        Scanner roomster = new Scanner(System.in);
        String testerr = roomster.nextLine();
        if (world.isRoom(testerr)) {
            System.out.println("you are now in" + testerr);
            player.move(world.getRoom(testerr));
        }
        if (testerr.equals("fight")) {
            //fightLoop((HostileNPC) world.getNPC("Wife"));
        }
        if (testerr.equals("arrest")) {
            player.addToJournal(world.getClue("Badge"));
            player.addToJournal(world.getClue("Dirty Darryl's Statement"));
            player.move(world.getRoom("Crime Scene"));
            arrest("");
            wantToQuit = true;
        }
        if (testerr.equals("convict")) {
            player.addToJournal(world.getClue("Badge"));
            player.addToJournal(world.getClue("Dirty Darryl's Statement"));
            player.move(world.getRoom("Police Department"));
            convict("");
            wantToQuit = true;
        }
        if (testerr.equals("drink")) {
            player.move(world.getRoom("Bar"));
            player.addToInventory(world.getItem("Beer"), player.getRoom());
            drink();
            System.out.println("this is how drunk you are");
            drunkness();
            wantToQuit = true;
        }
        if (testerr.equals("search")) {
            System.out.println("where do you want to search");
            Scanner whatareyouevendoing = new Scanner(System.in);
            String thisisshit = whatareyouevendoing.nextLine();
            if (world.isRoom(thisisshit)) {
                player.move(world.getRoom(thisisshit));
                search("");
            }

        }
        if (testerr.equalsIgnoreCase("talk")) {
            System.out.println("where do you want to talk");
            Scanner talkingmethod = new Scanner(System.in);
            String talkshit = talkingmethod.nextLine();
            if (world.isRoom(talkshit)) {
                player.move(world.getRoom(talkshit));
                talk("bla");
            }

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
    public List<Integer> getHUD() {
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
    public String getDrunkenness(){
        String percent=String.format("%02d",player.getDrunkenness());
        String drunkenness=("Drunkenness: "+percent+"%");
    return drunkenness;
    }

    public HiScoreManager getHiScoreManager() {
        return manager;
    }

    public int rollRooms() {
        int r = (int) (Math.random() * (5 - 1)) + 1;
        return r;
    }

    String test() {
        return "ye boi!";
    }

    public PC getPlayer() {
        return player;
    }

    public void NpcMover() {
//        System.out.println("NPCMover entered");//test
        for (Hobo hobo : world.getHobos()) {
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

    public void remover() {
        player.removeDrunkenness(1);
        

    }

    public void ProperTimer() {
        if (player.getMinutes() >= 60) {
            player.setHour(player.returnHours() + 1);
            player.setMinutes(0);
            if (player.getDrunkenness() >= 100) {
                System.out.println("You are completely smashed and pass out on the floor");

            }
            if (player.getDrunkenness() == 5) {
                System.out.println("You start to feel your hands again, if you dont drink soon you might die");
            }

            if (player.getDrunkenness() <= 0) {
                System.out.println("You feel completely sober, you fall down to the floor and die, knowing nobody loved you.");
                lose();
            }
        }
         if (player.getMinutes()%3== 0 && hobosOnTheMove == true) {
                NpcMover();
            }
    }

    public void timeloop(int runtimes) {
        int i;
        for (i = 0; i < runtimes; i++) {
            player.passTime(1);
            ProperTimer();
            remover();
        }
    }
}
