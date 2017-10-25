package pkg1st.semesterproject;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.Scanner;

public class Game {

    private Parser parser;
    private Room currentRoom;
    private Room leftStreet, rightStreet, bar, hoboAlley, crimeScene, partnerHome, home, pd, jail, court;
    private Item murderWeapon, bloodSplatteredBadge, gun, bloodSplatter, corpse;
    private NPC coroner, commissioner, bartender, wife, hobo1, hobo2, hobo3, hobo4;
    private Dialogue coronerDialogue, commissionerDialogue, bartenderDialogue, wifeDialogue, hobo1Dialogue, hobo2Dialogue, hobo3Dialogue, hobo4Dialogue;
    PC player;

    // Constructor calls createRooms and creates new Parser
    public Game() {
        createRooms();
        createItems();
        createDialogue();
        parser = new Parser();
        player = new PC(this, pd);

    }

    // Creates all rooms and their exits
    private void createRooms() {
        //todo

        leftStreet = new Room(" on left street", "Left Street");
        rightStreet = new Room(" on Right street", "Right Street");
        bar = new Room(" in the bar", "Bar");
        hoboAlley = new Room(" in the Hobo Alley, try not to get stabbed", "Hobo Alley");
        crimeScene = new Room(" at the crime scene", "Crime scene");
        partnerHome = new Room(" in your deceased partners house", "Partner's home");
        home = new Room(" in your home", "Home");
        pd = new Room(" in the Police Department", "Police Department");
        jail = new Room(" visiting the jail, in the pd", "Jail");
        court = new Room(" in court", "Court");

        //leftStreet exits
        leftStreet.setExit("east", rightStreet);
        leftStreet.setExit("south", bar);
        leftStreet.setExit("north", hoboAlley);
        leftStreet.setExit("west", partnerHome);

        //rightsteet exits
        rightStreet.setExit("east", home);
        rightStreet.setExit("south", court);
        rightStreet.setExit("north", pd);
        rightStreet.setExit("west", leftStreet);

        //leftstreet room exits
        hoboAlley.setExit("north", crimeScene);
        hoboAlley.setExit("south", leftStreet);

        crimeScene.setExit("south", hoboAlley);

        bar.setExit("north", leftStreet);

        partnerHome.setExit("east", leftStreet);

        //rightstreet room exits
        pd.setExit("north", jail);
        pd.setExit("south", rightStreet);

        jail.setExit("south", pd);

        home.setExit("west", rightStreet);

        court.setExit("north", rightStreet);

        currentRoom = bar;
    }

    public Room getRoom() {
        return currentRoom;
    }

    // Keeps game running requesting new command and ends the game
    // when processCommand returns true
    public void play() {
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
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
        getInfo();
    }

    // Excecutes commands
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

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
            search();
        } else if (commandWord == CommandWord.TALK) {
            talk();
        } else if (commandWord == CommandWord.ACCUSE) {
            accuse();
        } else if (commandWord == CommandWord.INVENTORY) {
            player.displayInventory();
        }
        return wantToQuit;
    }

    // Calls parser to show all possible commands
    private void printHelp() {
        System.out.println("You're supposed to help others, ");
        System.out.println("not get help.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    //Checks if directions has an exit and moves to next room
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            getInfo();
        }
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

    private void createDialogue() {

        //create dialogue
        String[] coronerLine = new String[]{
            "Welcome to the murder scene, make yourself at home.",
            "The victim is your partner, Detective Prickard. He was a dick, and the world is a better place without him.",
            "The victim was stabbed several times, and died from blood loss. It appears to be a crime of passion, due to the many stab wounds the spit on the victim’s face.",
            "The victim was surprised by the attack, so I believe he knew his killer. (update cluelist)",
            "I’ll get the cleaning team here know, so we can get this shit of the street. (loop; if played, NPC will disappear on next loading.)"
        };
        String[] wifeLine = new String[]{
            "What are you doing here? You love your job more than me, so go do your job and leave me alone.",
            "You are always out drinking, you sad piece of shit.",
            "Where were you even last night? You came home covered in shit all worked up?",
            "Go away, I want a good day. (end of dialogue: loop)"
        };
        String[] bartenderLine = new String[]{
            "Isn't it a bit early for you to be here?",
            "Detective Prickard is dead? I can’t say I’m surprised, he didn’t seem to get along with anyone, especially not you. (add to cluelist)",
            "I think you should get back at work now. (loop)"
        };
        String[] hobo1Line = new String[]{
            "Coppers don’t often come up to ‘dese parts",
            "I ‘eard a drunk fella’ shoutin’ at ’em.	(clue)",
            "Bugger off. (loop)"
        };
        String[] hobo2Line = new String[]{
            "We don’ take kindly to your kind ‘roun’ ‘ere!",
            "What de’ ye’ stinkin’ cops wan’ over ‘ere? (clue)",
            "Ay ain’t tellin’ ye’ nutin’, stupid cop. (loop)"
        };
        String[] hobo3Line = new String[]{
            "It gats’ ta’ be Darryl! He be lookin’ funny at me! (clue)",
            "Gat any smack? (loop)"
        };
        String[] hobo4Line = new String[]{
            "I bet your ass killed him, cops can’t help killing.",
            "I think you guilty, the voice in my head told me so. (loop)",};
        String[] commissionerLine1 = new String[]{
            "Detective, there has been a murder in the Hobo Alley, get over there ASAP. no time to wait for your partner, detective Prickard.",
            "Get your fat ass out of here! (loop)"
        };
        String[] commissionerLine2 = new String[]{
            "Did you find his badge? We need it for the memorial. (loop)"

        };

        //add dialogue to dialogue object
        Dialogue coronerDialogue = new Dialogue(coronerLine);
        Dialogue wifeDialogue = new Dialogue(wifeLine);
        Dialogue bartenderDialogue = new Dialogue(bartenderLine);
        Dialogue hobo1Dialogue = new Dialogue(hobo1Line);
        Dialogue hobo2Dialogue = new Dialogue(hobo2Line);
        Dialogue hobo3Dialogue = new Dialogue(hobo3Line);
        Dialogue hobo4Dialogue = new Dialogue(hobo4Line);
        Dialogue commissionerDialogue = new Dialogue(commissionerLine1, commissionerLine2);

        //create clues
        Clue testClue = new Clue("testName", "nondescript");
        Clue bartenderStatement=new Clue("Bartender's statement", "According to Bartender Bert everyone hated the victim.");
        Clue hobo1Statement=new Clue("No-Teeth Terry's statement", "According to No-Teeth Terry the murderer was a drunk man.");
        Clue hobo2Statement=new Clue("Dirty Darryl's statement", "Dirty Darryl obviously hates cops.");
        Clue hobo3Statement=new Clue("Heroin Harry's statement", "According to Heroin Harry Dirty Darryl is the killer.");
        Clue coronerStatement=new Clue("Coroner's statement", "According to the coroner the murder was a crime of passion, \nand the victim knew his killer.");
        

        NPC hobo1 = new NPC("No-Teeth Terry", hobo1Dialogue, hobo1Statement, 2);
        NPC hobo2 = new NPC("Dirty Darryl", hobo2Dialogue, hobo2Statement, 2);
        NPC hobo3 = new NPC("Heroin Harry", hobo3Dialogue, hobo3Statement, 1);
        NPC hobo4 = new NPC("Insane Dwayne", hobo4Dialogue, testClue, 0);
        NPC commissioner = new NPC("Commissioner Curt", commissionerDialogue, testClue, 0);
        NPC bartender = new NPC("Bartender Bert", bartenderDialogue, bartenderStatement, 2);
        NPC wife = new NPC("Wife", wifeDialogue, testClue, 0);
        NPC coroner = new NPC("Coroner", coronerDialogue, coronerStatement, 4);

        bar.addNpcToRoom(bartender);
        home.addNpcToRoom(wife);
        pd.addNpcToRoom(commissioner);
        crimeScene.addNpcToRoom(coroner);
        crimeScene.addNpcToRoom(hobo1);
        crimeScene.addNpcToRoom(hobo2);
        crimeScene.addNpcToRoom(hobo3);
        crimeScene.addNpcToRoom(hobo4);
 
    }



    private void createItems() {

        //create item clues
        Clue murderWeaponClue = new Clue("Murder Weapon Evidence", "The murder weapon is a broken bottle of your favorite beer.");
        Clue BloodsplatterClue = new Clue("Blood Splatter", "There was a lot of blood on the crimescene, suggesting a violent conflict.");
        Clue CorpseClue = new Clue("Corpse", "The body of the victim was stabbed repeatedly, and had spit covering it's face.");
        Clue bloodSplatteredBadgeClue = new Clue("Blood Splattered Badge evidence.", "This is the badge of the victim was found in your home, \nwhich points to you being the killer.");

        
        
        // creation of the multiple items via the constructor in Item.java
        Item murderWeapon = new Item("Murder Weapon", "This is a broken bottle"
                + " with sharp edges and blood covering the edges", true, true, murderWeaponClue);

        Item bloodSplatter = new Item("Blood splatter", "the ground is covered"
                + " in blood", true, false, BloodsplatterClue);

        Item gun = new Item("Gun", "Its a smith and wesson, your best friend",
                false, true, CorpseClue);

        Item corpse = new Item("Corpse", "its a dead guy, he looks to be stabbed"
                + " brutally multiple times.\n When you look closer you notice"
                + " his face is covered in spit", true, false, CorpseClue);

        Item bloodSplatteredBadge = new Item("Blood Splattered Badge", "its your"
                + " former partners badge covered in blood, odd that you would find"
                + " this here. \n i wave of guilt washes over you as you realise"
                + " what you have done", true, true,bloodSplatteredBadgeClue );

        Item test = new Item("test", "testDescipt", false, true, CorpseClue);

        bar.addItemsToRoom(test);
        crimeScene.addItemsToRoom(murderWeapon);
        crimeScene.addItemsToRoom(bloodSplatter);
        crimeScene.addItemsToRoom(corpse);
        pd.addItemsToRoom(gun);
        home.addItemsToRoom(bloodSplatteredBadge);

    }

    private void talk() {

        //Gives the player a list of NPCs in the room
        boolean sucess = false;
        do {
            
                
                System.out.println("Who do you wish to talk to?");
            for (NPC npc : currentRoom.getNpcsInRoom()) {
                System.out.println(npc.getName());
            }
            //have the player enter a name
            Scanner talking = new Scanner(System.in);
            String target = talking.nextLine().toLowerCase();

            //go through NPCs for matches to the input.
            for (NPC npc : currentRoom.getNpcsInRoom()) {
                if (target.equals(npc.getName().toLowerCase())) {
                    npc.getDialogue();

                    if (npc.getClueCount() == npc.getClueRelease()) {
                        player.addToCluelist(npc.giveClue());
                    }
                    boolean sucess1 = false;
                    do{

                        System.out.println("Do you want to keep talking?  Yes/No");

                        Scanner talking1 = new Scanner(System.in);
                        String target1 = talking1.nextLine().toLowerCase();

                        if (target1.equalsIgnoreCase("Yes")){
                            npc.getDialogue();
                            sucess1 = false;
                            if (npc.getClueCount() == npc.getClueRelease()) {
                                player.addToCluelist(npc.giveClue());
                            }
                        }


                        if (target1.equalsIgnoreCase("No")){
                            System.out.println("You decided not to talk anymore");
                            sucess1 = true;
                            sucess = true;
                        }    

                                      
              
                    
                    if (target1.equalsIgnoreCase("Yes")){
                    npc.getDialogue();
                    sucess1 = false;
                    }
                    
                    if (target1.equalsIgnoreCase("No")){
                    sucess1 = true;
                    }
                    else 
                        sucess = true;
                    } while (!sucess1);
                    break;
                }
                if (target.equalsIgnoreCase("Exit")) {
                    sucess = true;
                    break;
                }
                else {
                    System.out.println("There isnt anyone here by that name");
                }
            }
        } while (!sucess);

        //if no matches are found, print this line:
    }

    //todo the search is a mess
    //add to cluelist
    private void search() {

        //prints all items in the room.
        if (currentRoom.getItemsInRoom().size() != 0) {
            System.out.println("You found these items:");
            for (Item thing : currentRoom.getItemsInRoom()) {
                System.out.println(thing.getName());
            }
            System.out.println("What do you want to look at?\nIf you don't want anything, type \"nothing\".");

            //get an input for the desired item.
            Scanner pick = new Scanner(System.in);
            String newItem = pick.nextLine().toLowerCase();
            if (newItem.equals("nothing")) {
                System.out.println("I guess this is not interesting to you.\n");
            } else {
                //searches for the item
                boolean success = false;
                for (Item thing : currentRoom.getItemsInRoom()) {
                    if (newItem.equals(thing.getName().toLowerCase())) {
                        success = true;
                        System.out.println("\n" + thing.getDescription() + "\n");

                        if (thing.getCollectible() == true) {
                            System.out.println("Do you want to pick this item up? Yes/No");
                            String willing = pick.nextLine().toLowerCase();
                            if (willing.equals("yes") == true) {
                                player.addToInventory(thing, currentRoom);
                                if(thing.isClue==true){
                                player.addToCluelist(thing.giveClue());
                                }

                            } else
                            if (willing.equals("no")) {
                                System.out.println("The Item was left alone");
                                break;
                            }
                            else
                                System.out.println("I dont understand that");

                        } else {
                            System.out.println("This item can't be picked up.");
                            if(thing.isClue==true){
                            player.addToCluelist(thing.giveClue());
                            }
                            break;
                        }

                    }
                }
                if (success == false) {
                    System.out.println("You can't find that.");
                }
            }
        } else {
            System.out.println("You can't find anything.");
        }
    }

    public void accuse() {
        int points = player.getPoints();
        player.addPoints();
        if (points == 200) {
            System.out.println("you just completed the game");
        }
        
        System.out.println("You do not have the authority to do this.");
    }

    private void getInfo() {

        if (currentRoom.getNpcsInRoom().size() == 0) {
            System.out.println("You are all alone.");
        } else {
            System.out.println("The other people here are:");
            for (NPC npc : currentRoom.getNpcsInRoom()) {
                System.out.println(npc.getName());
            }
        }
    }
}
