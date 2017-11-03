package pkg1st.semesterproject;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.Scanner;

public class Game {

    private Parser parser;
    private Room currentRoom;
    private NPC commissioner;
    private Room leftStreet, rightStreet, bar, hoboAlley, crimeScene, partnerHome, home, pd, jail, court;
    private PC player;
    private Beverage beverage;
    boolean wantToQuit = false;
    private Clue bloodSplatteredBadgeClue = new Clue("Blood Splattered Badge evidence", "This is the badge of the victim was found in your home, \nwhich points to you being the killer.", true);
    private Item bloodSplatteredBadge = new Item("Blood Splattered Badge", "its your"
            + " former partners badge covered in blood, odd that you would find"
            + " this here. \n A wave of guilt washes over you as you realise"
            + " what you have done", true, true, bloodSplatteredBadgeClue);
    private Item partnerKey = new Item("Key to Partner's home", "This key belongs to your deceased partner, "
            + "and it allows you to enter his home.", false, true, null);

    // Constructor calls createRooms and creates new Parser
    public Game() {
        createRooms();
        createItems();
        createDialogue();
        parser = new Parser();
        player = new PC(pd);

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
        } else if (commandWord == CommandWord.ARREST) {
            arrest();
        } else if (commandWord == CommandWord.INSPECT) {
            inspect(command);
        } else if (commandWord == CommandWord.DROP) {
            drop(command);
        } else if (commandWord == CommandWord.LIE) {
            System.out.println("Lying is bad, and you should feel bad.");
        } else if (commandWord == CommandWord.CONVICT) {
            convict();
        } else if (commandWord == CommandWord.DRINK) {
            drink();
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
        if (what.equals("desk")) {
            player.diplayDesk(currentRoom);
        }
    }

    private void drop(Command command) {
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
                            player.moveToRoom(thing, currentRoom);
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
                                player.moveToRoom(thing, currentRoom);
                                success = true;
                            } else if (!newItem2.equals("nothing") || !newItem2.equals(thing.getName())) {
                                System.out.println("I did not understand that, try again");

                            }
                        }
                    }

                }
            }

            // if second word is desk and you are in PD
            if (where.equals("desk") && currentRoom == pd) {
                Scanner pick = new Scanner(System.in);

                System.out.println("Select the item to be dropped to in the desk or type \"nothing\" to exit drop");
                for (Item thing : player.getInventory()) {
                    System.out.println(thing.getName());
                    String newItem = pick.nextLine().toLowerCase();
                    boolean success = false;
                    while (!success) {

                        if (newItem.equals(thing.getName())) {
                            player.moveToDesk(thing);
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
                                player.moveToDesk(thing);
                                success = true;
                            } else if (!newItem2.equals("nothing") || !newItem2.equals(thing.getName())) {
                                System.out.println("I did not understand that, try again");

                            }
                        }
                    }

                }

            }
            // if second word is desk and not in PD
            if (where.equals("desk") && currentRoom != pd) {
                System.out.println("You can't reach your desk when you aren't in the police department.");
            }

        }
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
            if (nextRoom == partnerHome) {
                if (player.getInventory().contains(partnerKey)) {
                    currentRoom = nextRoom;
                    System.out.println(currentRoom.getLongDescription());
                    getInfo();
                } else {
                    System.out.println("The door is locked. You need a key to enter here.");
                }
            } else {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                getInfo();
                HostileNPC enemy = currentRoom.getJumped();
                if (enemy != null) {
                    fightLoop(enemy);
                }
            }
        }
        checkDrunkness();
        player.removeDrunkness(1);
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
        String coronerAlibi = "The coroners fingerprints and footprints are everywhere  on the crimescene"
                + " thats probaly because he is a coroner, his alibi checks out.";
        String wifeAlibi = "Of all the crimes this human waste has done, murder is sadly not one of them.";
        String commissionerAlibi = "Are you just accusing random people at this point? it is heresy to accuse Curt";
        String bartenderAlibi = "This man has no motive, obviously you are mistaken...";
        String hobo1Alibi = "With his broken english and his distrust for cops, this... thing is easily a suspect";
        String hobo2Alibi = "He called you stupid, which should be a crime punishable by death, you are certain this is the man";
        String hobo3Alibi = "Randomly throwing around accusations is often a sign of guilt, except when you do it, of course...";
        String hobo4Alibi = "This guy is insane... theres i no other way around it.";

        //add dialogue to dialogue object
        Dialogue coronerDialogue = new Dialogue(coronerLine, coronerAlibi, true);
        Dialogue wifeDialogue = new Dialogue(wifeLine, wifeAlibi, true);
        Dialogue bartenderDialogue = new Dialogue(bartenderLine, bartenderAlibi, true);
        Dialogue hobo1Dialogue = new Dialogue(hobo1Line, hobo1Alibi, false);
        Dialogue hobo2Dialogue = new Dialogue(hobo2Line, hobo2Alibi, false);
        Dialogue hobo3Dialogue = new Dialogue(hobo3Line, hobo3Alibi, false);
        Dialogue hobo4Dialogue = new Dialogue(hobo4Line, hobo4Alibi, false);
        Dialogue commissionerDialogue = new Dialogue(commissionerLine1, commissionerLine2, commissionerAlibi, true);

        //create clues
        Clue testClue = new Clue("testName", "nondescript", false);
        Clue bartenderStatement = new Clue("Bartender's statement", "According to Bartender Bert everyone hated the victim.", false);
        Clue hobo1Statement = new Clue("No-Teeth Terry's statement", "According to No-Teeth Terry the murderer was a drunk man.", false);
        Clue hobo2Statement = new Clue("Dirty Darryl's statement", "Dirty Darryl obviously hates cops.", true);
        Clue hobo3Statement = new Clue("Heroin Harry's statement", "According to Heroin Harry Dirty Darryl is the killer.", true);
        Clue hobo4Statement = new Clue("Insane Dwayne's statement", "Insane Dwayne is insane.", true);
        Clue coronerStatement = new Clue("Coroner's statement", "According to the coroner the murder was a crime of passion, \nand the victim knew his killer.", true);

        NPC hobo1 = new NPC("No-Teeth Terry", hobo1Dialogue, hobo1Statement, 2);
        NPC hobo2 = new NPC("Dirty Darryl", hobo2Dialogue, hobo2Statement, 2);
        NPC hobo3 = new NPC("Heroin Harry", hobo3Dialogue, hobo3Statement, 1);
        NPC hobo4 = new NPC("Insane Dwayne", hobo4Dialogue, hobo4Statement, 1);
        NPC commissioner = new NPC("Commissioner Curt", commissionerDialogue, testClue, 0);
        NPC bartender = new NPC("Bartender Bert", bartenderDialogue, bartenderStatement, 2);
        HostileNPC wife = new HostileNPC("Wife", wifeDialogue, testClue, 0, 50, 5, 0.5);
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
        Clue murderWeaponClue = new Clue("Murder Weapon Evidence", "The murder weapon is a broken bottle of your favorite beer.", true);
        Clue BloodsplatterClue = new Clue("Blood Splatter", "There was a lot of blood on the crimescene, suggesting a violent conflict.", false);
        Clue CorpseClue = new Clue("Corpse", "The body of the victim was stabbed repeatedly, and had spit covering it's face.", false);

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
        Beverage beer = new Beverage("Beer", "Its a well known brand called pisswasser", false, true, null, 2, 2);

        Item test = new Item("test", "testDescipt", false, true, CorpseClue);

        bar.addItemsToRoom(test);
        hoboAlley.addItemsToRoom(murderWeapon);
        crimeScene.addItemsToRoom(bloodSplatter);
        crimeScene.addItemsToRoom(corpse);
        pd.addItemsToRoom(gun);
        bar.addItemsToRoom(beer);
        //pd.addItemsToRoom(partnerKey);

    }

    private void talk() {

        //Gives the player a list of NPCs in the room
        boolean sucess = false;
        player.removeDrunkness(1);
        do {

            System.out.println("Who do you wish to talk to?");
            for (NPC npc : currentRoom.getNPCsInRoom()) {
                System.out.println(npc.getName());
            }
            //have the player enter a name
            Scanner talking = new Scanner(System.in);
            String target = talking.nextLine().toLowerCase();
            if (target.equalsIgnoreCase("exit")) {
                sucess = true;
                break;
            }
            //go through NPCs for matches to the input.
            boolean success = false;
            for (NPC npc : currentRoom.getNPCsInRoom()) {

                if (target.equals(npc.getName().toLowerCase())) {
                    npc.getLine();
                    success = true;
                    if (npc.getClueCount() == npc.getClueRelease()) {
                        player.addToJournal(npc.giveClue());
                    }
                    boolean sucess1 = false;
                    do {
                        System.out.println("Do you want to keep talking?  Yes/No");

                        Scanner talking1 = new Scanner(System.in);
                        String target1 = talking1.nextLine().toLowerCase();

                        if (target1.equalsIgnoreCase("Yes")) {
                            npc.getLine();
                            sucess1 = false;
                            if (npc.getClueCount() == npc.getClueRelease()) {
                                player.addToJournal(npc.giveClue());
                            }
                        }

                        if (target1.equalsIgnoreCase("No")) {
                            System.out.println("You decided not to talk anymore");
                            sucess1 = true;
                            sucess = true;
                        }
                    } while (!sucess1);
                    break;
                }

            }
            if (!success) {
                System.out.println("There isnt anyone here by that name");

            }
        } while (!sucess);

        //if no matches are found, print this line:
    }

    //todo the search is a mess
    //add to cluelist
    private void search() {
        player.removeDrunkness(1);

        //prints all items in the room.
        if (!currentRoom.getItemsInRoom().isEmpty()) {
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
                            if (willing.equals("yes")) {
                                player.addToInventory(thing, currentRoom);
                                if (thing == bloodSplatteredBadge) {
                                    parser.addFinishers();
                                }
                                if (thing.isClue) {
                                    player.addToJournal(thing.giveClue());
                                    break;
                                }

                            } else if (willing.equals("no")) {
                                System.out.println("The Item was left alone");
                                break;
                            } else {
                                System.out.println("I dont understand that");
                            }

                        } else {
                            System.out.println("This item can't be picked up.");
                            if (thing.isClue == true) {
                                player.addToJournal(thing.giveClue());
                                thing.setIsClue();
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

    public void arrest() {
        player.removeDrunkness(1);
        System.out.println("You have decided to begin arresting people, god bless you");
        System.out.println("Are you sure?   Yes/No");
        Scanner accusing = new Scanner(System.in);
        String victim = accusing.nextLine().toLowerCase();
        if (victim.equalsIgnoreCase("no")) {
            System.out.println("You decided not to accuse anyone... for now");
        } else if (victim.equalsIgnoreCase("yes")) {
            System.out.println("These are the people you can accuse:");
            for (NPC npc : currentRoom.getNPCsInRoom()) {
                System.out.println(npc.getName());
            }

            Scanner choose = new Scanner(System.in);
            String person = choose.nextLine().toLowerCase();
            boolean success = false;
            for (NPC npc : currentRoom.getNPCsInRoom()) {
                if (person.equals(npc.getName().toLowerCase())) {
                    if (npc.getAlibi()) {
                        lose();
                        success = true;
                        break;
                    } else {
                        goToJail(npc);
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

    private void getInfo() {

        if (currentRoom.getNPCsInRoom().isEmpty()) {
            System.out.println("You are all alone.");
        } else {
            System.out.println("The other people here are:");
            for (NPC npc : currentRoom.getNPCsInRoom()) {
                System.out.println(npc.getName());
            }
        }
    }

    public void drink() {
        System.out.println("drink method");
        for (Item drink : player.getInventory()) {
            System.out.println("For loop");
            if (drink instanceof Beverage) {
                System.out.println("You drink some " + drink.getName() + ", you start to feel all your problems disappear");
                player.addDrunkness(((Beverage) drink).getAlcholContent());
                ((Beverage) drink).removeSip();

            }
        }

    }

    public void goToJail(NPC scum) {
        System.out.println("You moved the scum to jail.");
        currentRoom.removeNpcFromRoom(scum);
//        pd.removeNpcFromRoom(commissioner);
        jail.addNpcToRoom(scum);
        currentRoom = jail;
        System.out.println("Commissioner: Good job, now you need to go find "
                + "some better evidence to convict this bastard. I will be in the Police department.");
        home.addItemsToRoom(bloodSplatteredBadge);
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
        wantToQuit = true;
    }

    public void checkDrunkness() {
        if (player.getDrunkness() == 6) {
            System.out.println("you feel your buzz start to fade, you need a drink");
        }
        if (player.getDrunkness() == 3) {
            System.out.println("CRITICAL SOBER LEVELS DRINK IMMEDIATELY");
        }

        if (player.getDrunkness() == 0) {
            System.out.println("You feel completely sober, you fall down to the floor and die, knowing nobody loved you");
            lose();
            wantToQuit = true;
        }

    }

    public void convict() {
        player.removeDrunkness(1);
        if (currentRoom == pd) {
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
                                for(NPC npc:jail.getNPCsInRoom()){
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

    public void fightLoop(HostileNPC enemy) {
        player.removeDrunkness(1);
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
                        currentRoom.removeNpcFromRoom(enemy);
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
    }

    private int damageRandomizer() {
        return ((int) (Math.random() * 11) - 5);
    }

}
