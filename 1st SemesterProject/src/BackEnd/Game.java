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
import BackEnd.Command.Parser;
import BackEnd.Command.CommandWord;
import BackEnd.Command.Command;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Game {

    private Parser parser;
    boolean wantToQuit = false;
    private PC player;
    private World world=new World();
    private HiScoreManager manager=new HiScoreManager();
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
        if (tester.equals("y")){
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

    // Prints the welcome message, the help command, and the current room
    private void printWelcome() {
        //todo
        System.out.println();
        System.out.println("Welcome to the life of detective Dindunuffin.");
        System.out.println("The commissioner wants you in the police department.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println("Use the \"talk\" command to talk to the Bartender.");
        System.out.println();
        System.out.println(player.getRoom().getLongDescription());
        getInfo();
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
            goRoom(command);
        } else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        } else if (commandWord == CommandWord.SEARCH) {
            search("");
        } else if (commandWord == CommandWord.TALK) {
            talk("");
        } else if (commandWord == CommandWord.ARREST) {
            arrest("");
        } else if (commandWord == CommandWord.INSPECT) {
            inspect(command);
        } else if (commandWord == CommandWord.DROP) {
            drop(command);
        } else if (commandWord == CommandWord.LIE) {
            System.out.println("Lying is bad, and you should feel bad.");
        } else if (commandWord == CommandWord.CONVICT) {
            convict("");
        } else if (commandWord == CommandWord.DRINK) {
            drink();
        } else if (commandWord == CommandWord.DRUNKNESS){
            drunkness();
        }
        return wantToQuit;
    }

    // Calls parser to show all possible commands
    private void printHelp() {
        
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    Set<String> inspectMenu(){
     System.out.println("Who do you wish to talk to?");
     return player.getRoom().getNPCsInRoomMap().keySet();
     //todo
     }
    
    
    
    private void inspect(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Inspect what? inventory or journal");
            return;
        }

        String what = command.getSecondWord();

        if (what.equals("inventory")) {
            player.displayInventory();
        }
        if (what.equals("journal")) {
            player.displayJournal();
        }
//        if (what.equals("desk")) {
//            player.diplayDesk(currentRoom);
//        }     todo remove?
    }
    //Checks if directions has an exit and moves to next room
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = player.getRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            if (nextRoom == world.getRoom("Partner's Home")) {
                if (player.getInventory().contains(world.getItem("Key To Partner's Home"))) {
                    player.move(nextRoom);
                    System.out.println(player.getRoom().getLongDescription());
                    getInfo();
                } else {
                    System.out.println("The door is locked. You need a key to enter here.");
                }
            } else {
                
                player.moveBack();
                player.move(nextRoom);
                System.out.println(player.getRoom().getLongDescription());
                getInfo();
                HostileNPC enemy = player.getRoom().getJumped();
                if (enemy != null) {
                    fightLoop(enemy);
                }
            }
        }
        Checker();
        remover();
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
            System.out.println("You are all alone.");
        } else {
            System.out.println("The other people here are:");
            for (NPC npc : player.getRoom().getNPCsInRoom()) {
                System.out.println(npc.getName());
                
            }
        }
    }

    public void drink() {
        System.out.println("drinking");
        for (Item drink : player.getInventory()) {
            if (drink instanceof Beverage) {
                System.out.println("You drink some " + drink.getName() + ", you start to feel all your problems disappear");
                player.addDrunkness(((Beverage) drink).getAlcoholContent());
                ((Beverage) drink).removeSip();
                if(((Beverage) drink).getNumberOfSips()==0){
                player.getInventory().remove(drink);
                    System.out.println("You emptied your bottle and tossed it away.");
                }
                System.out.println(player.getDrunkness());
                break;
            }
        }

    }

    public void goToJail(NPC scum) {
        remover();
        System.out.println("You moved the scum to jail.");
        player.getRoom().moveNpc(scum, world.getRoom("Jail"));
        player.move(world.getRoom("Jail"));
        System.out.println("Commissioner: Good job, now you need to go find "
                + "some better evidence to convict this bastard. I will be in the Police department.");
        world.getRoom("Home").addItemsToRoom(world.getItem("Badge"));
        parser.addFinishers();
        
    }

    public void lose() {
        System.out.println("You lost.");
        if (player.getPoints() >= 100) {
            System.out.println("You lost the game. you were rated a " + (player.getPoints() - 100) + " percent good cop.");
        } else {
            System.out.println("You lost the game. you were rated a " + (100 - player.getPoints()) + " percent bad cop.");
        }
        wantToQuit = true;
    }

    public void win() {
        if (player.getPoints() >= 100) {
            System.out.println("Congratulations, you won the game! you were rated a " + (player.getPoints() - 100) + " percent good cop.");
        } else {
            System.out.println("Congratulations, you won the game! you were rated a " + (100 - player.getPoints()) + " percent bad cop.");
        }
        //todo make highscore application
        wantToQuit = true;
    }

    public void Checker() {
        if (player.getDrunkness() == 15) {
            System.out.println("you feel your buzz start to fade, you need a drink");
        }
        if (player.getDrunkness() == 5) {
            System.out.println("You start to feel your hands again, if you dont drink soon you might die");
        }

        if (player.getDrunkness() == 0) {
            System.out.println("You feel completely sober, you fall down to the floor and die, knowing nobody loved you.");
            lose();
        }
            switch (player.getMovementChecker()) {
                case 12: 
                    rollRooms();
                    
                    player.getRoom().moveNpc(world.getHostileNPC("Dirty Darryl"), world.getRoom("Right Street"));
                    player.getRoom().moveNpc(world.getHostileNPC("Heroin Harry"), world.getRoom("Hobo Alley"));
                    break;
                case 28:
                    player.getRoom().moveNpc(world.getHostileNPC("Insane Dwayne"), world.getRoom("Hobo Alley"));
                    player.getRoom().moveNpc(world.getHostileNPC("Dirty Darryl"), world.getRoom("Left Street"));
                    player.getRoom().moveNpc(world.getHostileNPC("Heroing Harry"), world.getRoom("Crime Scene"));
                    player.getRoom().moveNpc(world.getHostileNPC("No-Teeth Terry"), world.getRoom("Hobo Alley"));
                    break;

                
            } 
    }
        
    public void drunkness() {
        System.out.println(player.getDrunkness());
    }
    
    private int damageRandomizer() {
        return ((int) (Math.random() * 11) - 5);
    }
    private void updateCrimeScene(){        
        Iterator<NPC> iterator = world.getRoom("Crime Scene").getNPCsInRoom().iterator();

    while(iterator.hasNext()) {
        NPC npc = iterator.next();
        if(npc.getName().equals("Coroner")){
                iterator.remove();
            }else{        
        world.getRoom("Hobo Alley").addNpcToRoom(npc);
        iterator.remove();
        world.getHostileNPC(npc.getName()).setAggression(0.3);
                }          
            }
    Item corpseOutline = new Item("Corpse Outline", "its was a dead guy, he looked like he was stabbed"
                + " brutally multiple times.\n When you looked closer you noticed"
                + " his face is covered in spit", true, false, world.getClue("Corpse"));
        world.getRoom("Crime Scene").addItemsToRoom(corpseOutline);
        world.getRoom("Crime Scene").removeItemFromRoom(world.getItem("Corpse"));
        world.getRoom("Crime Scene").removeItemFromRoomMap("Corpse");
        
        }
    
    public void fightLoop(HostileNPC enemy) {
        remover();
        int playerHp = player.getCurrentHealth();
        int enemyHp = enemy.getHealth();
        int playerDmg = 10;
        int enemyDmg = enemy.getDamage();
        boolean keepFighting = true;
        int random1;
        int random2;
        
        Scanner fightCommander = new Scanner(System.in);
        String fightCommand;
        System.out.println("You are now fighting a " + enemy.getName() + ". For your options, type 'help'.");
        enemy.getFightScream();

        if (player.inventoryContains("gun")) {
            playerDmg = 30;
            System.out.println("You draw your gun.");
        }
        while (keepFighting) {
            fightCommand = fightCommander.nextLine().toLowerCase();

            switch (fightCommand) {
                case "run":
                    if (Math.random() < 0.7) {
                        keepFighting = false;
                        System.out.println("You ran away like a coward.");
                        player.moveBack();
                        System.out.println(player.getRoom().getLongDescription());
                getInfo();
                    } else {
                        System.out.println("Your opponent didn't let you escape, and you got hit.");
                        random1 = damageRandomizer();
                        playerHp -= enemyDmg + random1;
                        System.out.println("You took " + (enemyDmg + random1) + " damage.");
                    }
                    break;
                case "fight":
                    random1 = damageRandomizer();
                    random2 = damageRandomizer();
                    System.out.println("You strike your opponent and deal " + (playerDmg + random1) + " damage.");
                    enemyHp -= (playerDmg + random1);
                    if (enemyHp <= 0) {
                        System.out.println("You defeated you oppoent!");
                        keepFighting = false;
                        player.removePoints(15);
                        player.getRoom().removeNpcFromRoom(enemy);
                        break;
                    }
                    System.out.println("Your opponent retaliates, dealing " + (enemyDmg + random2) + " damage.");
                    playerHp -= (enemyDmg + random2);
                    break;
                case "help":
                    System.out.println("your options are:\nfight: Attack your opponent, "
                            + "and take a hit.\nrun: Attempt to run away from your "
                            + "opponent. Beware, escape is not certain.\n calm: Attempt to "
                            + "calm your opponent down. beware, if you fail, your "
                            + "opponent will still attack you.");
                    break;
                case "calm":
                    if (Math.random() < 0.1) {
                        keepFighting = false;
                        System.out.println("You managed to calm down your opponent.");
                        enemy.calmDown();
                        player.addPoints(10);
                    } else {
                        random1 = damageRandomizer();
                        playerHp -= enemyDmg + random1;
                        System.out.println("You failed to calm your opponent, and got "
                                + "struck. you took " + (enemyDmg + random1) + " damage.");
                    }
                    break;
                default:
                    System.out.println("Now is not the time to kid around.");
            }
            if (playerHp <= 0) {
                System.out.println("You died.");
                lose();
                keepFighting = false;
            }
        }
        player.setCurrentHealth(playerHp);
        getInfo();
    }
    
    Set<String> convictMenu(){
     System.out.println("Who do you wish to talk to?");
     return player.getRoom().getNPCsInRoomMap().keySet();
     //todo
     }
    
    public void convict(String clue) {
            //todo
        remover();
        if (player.getRoom() == world.getRoom("Police Department")) {
            boolean run = true;
            boolean confess=false;
            do {
                Scanner convicting = new Scanner(System.in);
                System.out.println("Select a clue.");
                player.displayJournal();
                String evidence = convicting.nextLine().toLowerCase();
                if (!evidence.equalsIgnoreCase("exit")) {
                    if (player.getJournal().containsKey(evidence)) {
                        if (evidence.equalsIgnoreCase("Blood Splattered Badge evidence")) {
                            System.out.println("Commisioner: Where did you find this? This evidence could be used to convict anyone.");
                            do {
                                System.out.println("(You can now Lie or Confess, if you lie an inocent man will be imprisoned, if you confess you'll be.)(lie/confess)");

                                Scanner willing = new Scanner(System.in);
                                String will = willing.nextLine().toLowerCase();

                                if (will.equals("confess")) {
                                    System.out.println("You told the truth and confessed to your crime.");
                                    player.addPoints(20);
                                    confess=true;
                                    win();
                                    break;
                                } else if (will.equals("lie")) {
                                    System.out.println("You lied");
                                    player.removePoints(40);
                                    break;
                                } else {
                                    System.out.println("This is serious.");
                                }
                            } while (true);
                            if(confess){
                            break;
                            }
                        }
                        if (player.getJournal().get(evidence).isConvictable()) {
                            player.addToevidence(evidence);
                            System.out.println(evidence + " has been added to Evidence list");
                            if (player.isEvidence2()) {
                                System.out.print("Judge: We have found ");
                                for(NPC npc:world.getRoom("Jail").getNPCsInRoom()){
                                System.out.print(npc.getName()+(" "));
                                }
                                System.out.print("guilty of murder.");

                                win();
                                break;
                            } else {
                                while (true) {
                                    System.out.println("Do you want to add another piece of evidence?");
                                    Scanner willing = new Scanner(System.in);
                                    String will = willing.nextLine().toLowerCase();

                                    if (will.equals("yes")) {
                                        run = true;
                                        break;
                                    }
                                    if (will.equals("no")) {
                                        run = false;
                                        break;
                                    } else {
                                        System.out.println("I don't understand");
                                    }
                                }
                            }
                        } else {
                            System.out.println(evidence + " is not good enough to use in Court");
                        }
                    } else {
                        System.out.println("That is not evidence.");
                    }
                } else {
                    run = false;
                }
            } while (run);
        } else {
            System.out.println("You are not near the commisioner, so you can't do this.");
        }

    }
    
     void drop(Command command) {
        remover();
        String where = command.getSecondWord();
        //if no second word    
        if (!command.hasSecondWord()) {
            System.out.println("Drop where? to room or desk");
            return;
        }
        //if inventory is empty
        if (player.getInventory().isEmpty()) {
            System.out.println("You can't drop anything because you don't have anything on you.");
        } else {
            //if second word is room
            if (where.equals("room")) {
                Scanner pick = new Scanner(System.in);

                System.out.println("Select the item to be dropped to the room or type \"nothing\" to exit drop");
                for (Item thing : player.getInventory()) {
                    System.out.println(thing.getName());
                    String newItem = pick.nextLine().toLowerCase();
                    boolean success = false;
                    while (!success) {

                        if (newItem.equals(thing.getName())) {
                            player.moveToRoom(thing, player.getRoom());
                            success = true;
                        }

                        if (newItem.equals("nothing")) {
                            success = true;
                            break;
                        } else {
                            System.out.println("Drop what? or nothing at all? type the item you want to drop or \"nothing\" to exit.");
                            String newItem2 = pick.nextLine().toLowerCase();
                            if (newItem2.equals("nothing")) {
                                success = true;
                                break;
                            }
                            if (newItem2.equals(thing.getName())) {
                                player.moveToRoom(thing, player.getRoom());
                                success = true;
                            } else if (!newItem2.equals("nothing") || !newItem2.equals(thing.getName())) {
                                System.out.println("I did not understand that, try again");

                            }
                        }
                    }

                }
            }

            // if second word is desk and you are in PD
            if (where.equals("desk") && player.getRoom() == world.getRoom("Police Department")) {
                Scanner pick = new Scanner(System.in);

                System.out.println("Select the item to be dropped to in the desk or type \"nothing\" to exit drop");
                for (Item thing : player.getInventory()) {
                    System.out.println(thing.getName());
                    String newItem = pick.nextLine().toLowerCase();
                    boolean success = false;
                    while (!success) {

                        if (newItem.equals(thing.getName())) {
                            //player.moveToDesk(thing);
                            success = true;
                        }

                        if (newItem.equals("nothing")) {
                            success = true;
                            break;
                        } else {
                            System.out.println("Drop what? or nothing at all? type the item you want to drop or \"nothing\" to exit.");
                            String newItem2 = pick.nextLine().toLowerCase();
                            if (newItem2.equals("nothing")) {
                                success = true;
                                break;
                            }
                            if (newItem2.equals(thing.getName())) {
                                //player.moveToDesk(thing);
                                success = true;
                            } else if (!newItem2.equals("nothing") || !newItem2.equals(thing.getName())) {
                                System.out.println("I did not understand that, try again");

                            }
                        }
                    }

                }

            }
            // if second word is desk and not in PD
            if (where.equals("desk") && player.getRoom() != world.getRoom("Police Department")) {
                System.out.println("You can't reach your desk when you aren't in the police department.");
            }

        }
    }
     Set<String> talkMenu(){
     if(!player.getRoom().getNPCsInRoomMap().isEmpty()){    
     System.out.println("Who do you wish to talk to?");
     return player.getRoom().getNPCsInRoomMap().keySet();}
     else{
         System.out.println("You are all alone.");
     return null;
     }
     }
     
     
     void talk(String name) {
        //Gives the player a list of NPCs in the room
        NPC target=player.getRoom().getNPCsInRoomMap().get(name);
        target.getLine();
        if (target.getClueCount() == target.getClueRelease()) {
                        player.addToJournal(target.giveClue());
                    }
     }

     Set<String> searchMenu(){
        if(!player.getRoom().getItemsInRoomMap().isEmpty()){    
     System.out.println("You found these items.\nWhat do you want to look at?");
     return player.getRoom().getItemsInRoomMap().keySet();
        }
     else{
         System.out.println("You can't find anything.");
     return null;
     }

     }
     
     boolean search(String name) {
         Item item=world.getItem(name);
         System.out.println("\n" + item.getDescription() + "\n");
         
                                 if (item.getCollectible()) {
                            System.out.println("Do you want to pick this item up? Yes/No");
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
     void pickup(String string){
     
        Item item =world.getItem(string);
                                
        if (item.getIsClue()) {
        player.addToJournal(item.giveClue());
        }
        player.addToInventory(item, player.getRoom());
    
    }

     boolean ask(){
     return true;
     }
     
     Set<String> arrestMenu(){
     System.out.println("Who do you wish to talk to?");
     return player.getRoom().getNPCsInRoomMap().keySet();
     }
     
    public void arrest(String name) {
        remover();
        System.out.println("You have decided to begin arresting people, god bless you");
        System.out.println("Are you sure?   Yes/No");
        Scanner accusing = new Scanner(System.in);
        String victim = accusing.nextLine().toLowerCase();
        if (victim.equalsIgnoreCase("no")) {
            System.out.println("You decided not to accuse anyone... for now");
        } else if (victim.equalsIgnoreCase("yes")) {
            System.out.println("These are the people you can accuse:");
            for (NPC npc : player.getRoom().getNPCsInRoom()) {
                System.out.println(npc.getName());
            }
            Scanner choose = new Scanner(System.in);
            String person = choose.nextLine();
            boolean success = false;
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
            for (NPC npc : player.getRoom().getNPCsInRoom()) {
                if (person.equals(npc.getName().toLowerCase())) {
                    if (npc.getAlibi()) {
                        lose();
                        success = true;
                        break;
                    } else {
                        goToJail(npc);
                        updateCrimeScene();
                    }
                    success = true;
                    break;
                }

            }
            if (!success) {
                System.out.println("If you're not going to be serious about this, find another job.");
            }
        } else {
            System.out.println("Can't you just answer a simple Yes/No question?");
        }

    }
    public void tester(){
              System.out.println("write the method or room you would like to test");
 
            Scanner roomster = new Scanner(System.in);
            String testerr = roomster.nextLine();
            if (world.isRoom(testerr)){
                System.out.println("you are now in" + testerr);
                player.move(world.getRoom(testerr));
            }
            if (testerr.equals("fight")){
                fightLoop((HostileNPC) world.getNPC("Wife"));
            }
            if (testerr.equals("arrest")){
                player.addToJournal(world.getClue("Badge"));
                player.addToJournal(world.getClue("Dirty Darryl's Statement"));
                player.move(world.getRoom("Crime Scene"));
                arrest("");
                wantToQuit = true;
            }
            if (testerr.equals("convict")){
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
            if (testerr.equals("search")){
                System.out.println("where do you want to search");
                Scanner whatareyouevendoing = new Scanner(System.in);
                String thisisshit = whatareyouevendoing.nextLine();
                if (world.isRoom(thisisshit)){
                    player.move(world.getRoom(thisisshit));
                    search("");
                }

            }
            if (testerr.equalsIgnoreCase("talk")){
                System.out.println("where do you want to talk");
                Scanner talkingmethod = new Scanner(System.in);
                String talkshit = talkingmethod.nextLine();
                if (world.isRoom(talkshit)){
                    player.move(world.getRoom(talkshit));
                    talk("bla");
                }

            }
    
    }
    public void remover() {
        player.removeDrunkness(1);
        player.passTime(2);
        
    }
    public HiScoreManager getHiScoreManager(){
    return manager;
    }

    public int rollRooms(){
        int r = (int) (Math.random() * 2 + 5);
        return r; 
    }
    String test(){
    return "ye boi!";
    }
    }


