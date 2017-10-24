package pkg1st.semesterproject;

/**
 * @author  Michael Kolling and David J. Barnes
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
    createNPCs();
    createItems();
    createDialogue();
    fillRooms();
    parser = new Parser();
    player=new PC(this, pd);
    
}

// Creates all rooms and their exits
private void createRooms() {
    //todo
    
    leftStreet = new Room(" on left street","Left Street");
    rightStreet = new Room(" on Right street","Right Street");
    bar = new Room(" in the bar","Bar");
    hoboAlley = new Room(" in the Hobo Alley, try not to get stabbed","Hobo Alley");
    crimeScene = new Room(" at the crime scene","Crime scene");
    partnerHome = new Room(" in your deceased partners house","Partner's home");
    home = new Room(" in your home","Home");
    pd = new Room(" in the Police Department","Police Department");
    jail = new Room(" visiting the jail, in the pd","Jail");
    court = new Room(" in court","Court");
    
    //leftStreet exits
    leftStreet.setExit("east", rightStreet);
    leftStreet.setExit("south",bar);
    leftStreet.setExit("north", hoboAlley);
    leftStreet.setExit("west", partnerHome);
    
    //rightsteet exits
    rightStreet.setExit("east", home);
    rightStreet.setExit("south",court);
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
    
      
public Room getRoom(){
    return currentRoom;
}

// Keeps game running requesting new command and ends the game
// when processCommand returns true
public void play()
{
    printWelcome();
    
    boolean finished = false;
    while (! finished) {
        
        Command command = parser.getCommand();
        finished = processCommand(command);
        System.out.println("");
        
    }
    System.out.println("Thank you for playing.  Good bye.");
}

// Prints the welcome message, the help command, and the current room
private void printWelcome()
{ //todo
    System.out.println();
    System.out.println("Welcome to the life of detective Dindunuffin.");
    System.out.println("The commissioner wants you in the police department.");
    System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
    System.out.println();
    System.out.println(currentRoom.getLongDescription());
    getInfo();
}

// Excecutes commands
private boolean processCommand(Command command)
{
    boolean wantToQuit = false;
    
    CommandWord commandWord = command.getCommandWord();
    
    if(commandWord == CommandWord.UNKNOWN) {
        System.out.println("I don't know what you mean...");
        return false;
    }
    
    if (commandWord == CommandWord.HELP) {
        printHelp();
    }
    else if (commandWord == CommandWord.GO) {
        goRoom(command);
    }
    else if (commandWord == CommandWord.QUIT) {
        wantToQuit = quit(command);
    }
    else if (commandWord == CommandWord.SEARCH){
        search();
    }
    else if (commandWord == CommandWord.TALK){
        talk();
    }
    else if (commandWord == CommandWord.ACCUSE){
        accuse();
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
    if(!command.hasSecondWord()) {
        System.out.println("Go where?");
        return;
    }
    
    String direction = command.getSecondWord();
    
    Room nextRoom = currentRoom.getExit(direction);
    
    if (nextRoom == null) {
        System.out.println("There is no door!");
    }
    else {
        currentRoom = nextRoom;
        System.out.println(currentRoom.getLongDescription());
        getInfo();
    }
}

// Quits the game
private boolean quit(Command command) {
    if(command.hasSecondWord()) {
        System.out.println("Quit what?");
        return false;
    }
    else {
        return true;
    }
}

private void createDialogue() {
    
    //create dialogue
    String[] coronerLine=new String[]{
        "Welcome to the murder scene, make yourself at home.",
        "The victim is your partner, Detective Prickard. He was a dick, and the world is a better place without him.",
        "The victim was stabbed several times, and died from blood loss. It appears to be a crime of passion, due to the many stab wounds the spit on the victim’s face. (update cluelist)",
        "The victim was surprised by the attack, so I believe he knew his killer. (update cluelist)",
        "I’ll get the cleaning team here know, so we can get this shit of the street. (loop; if played, NPC will disappear on next loading.)"
    };
    String[] wifeLine=new String[]{
        "What are you doing here? You love your job more than me, so go do your job and leave me alone.",
        "You are always out drinking, you sad piece of shit.",
        "Where were you even last night? You came home covered in shit all worked up?",
        "Go away, I want a good day. (end of dialogue: loop)"
    };
    String[] bartenderLine=new String[]{
        "You’re here early, just the usual? (add drink to inventory)",
        "Detective Prickard is dead? I can’t say I’m surprised, he didn’t seem to get along with anyone, especially not you. (add to cluelist)",
        "I think you should get back at work now. (loop)"
    };
    String[] hobo1Line=new String[]{
        "Coppers don’t often come up to ‘dese parts",
        "I ‘eard a drunk fella’ shoutin’ at ’em.	(clue)",
        "Bugger off. (loop)"
    };
    String[] hobo2Line=new String[]{
        "We don’ take kindly to your kind ‘roun’ ‘ere!",
        "What de’ ye’ stinkin’ cops wan’ over ‘ere? (clue)",
        "Ay ain’t tellin’ ye’ nutin’, stupid cop. (loop)"
    };
    String[] hobo3Line=new String[]{
        "It gats’ ta’ be Rodney! He be lookin’ funny at me! (clue)",
        "Gat any smack? (loop)"
    };
    String[] hobo4Line=new String[]{
        "I bet your ass killed him, cops can’t help killing.",
        "I think you guilty, the voice in my head told me so. (loop)",
    };
    String[] commissionerLine1=new String[]{
        "Detective, there has been a murder in the Hobo Alley, get over there ASAP. no time to wait for your partner, detective Prickard.",
        "Get your fat ass out of here! (loop)"
    };
    String[] commissionerLine2=new String[]{
        "Did you find his badge? We need it for the memorial. (loop)"
            
    };
    
    //add dialogue to dialogue object
    
    Dialogue coronerDialogue=new Dialogue(coronerLine);
    Dialogue wifeDialogue=new Dialogue(wifeLine);
    Dialogue bartenderDialogue=new Dialogue(bartenderLine);
    Dialogue hobo1Dialogue=new Dialogue(hobo1Line);
    Dialogue hobo2Dialogue=new Dialogue(hobo2Line);
    Dialogue hobo3Dialogue=new Dialogue(hobo3Line);
    Dialogue hobo4Dialogue=new Dialogue(hobo4Line);
    Dialogue commissionerDialogue=new Dialogue(commissionerLine1,commissionerLine2);
    
}

//Creates NPCs
private void createNPCs(){
    NPC hobo1 = new NPC("No-Teeth Terry", hobo1Dialogue);
    NPC hobo2 = new NPC("Dirty Darryl" ,hobo2Dialogue);
    NPC hobo3 = new NPC("Heroin Harry" ,hobo3Dialogue);
    NPC hobo4 = new NPC("Insane Dwayne",hobo4Dialogue);
    NPC commissioner = new NPC("Commissioner Curt" ,commissionerDialogue);
    NPC bartender = new NPC("Bartender Bert" ,bartenderDialogue);
    NPC wife = new NPC("Wife Nancy = new Wife()",wifeDialogue );
    NPC coroner = new NPC("Coroner" ,coronerDialogue);
    
    bar.addNpcToRoom(bartender);
    //todo move placements to fillRooms()
}
private void fillRooms(){
    
    
}


private void createItems(){
    
    // creation of the multiple items via the constructor in Item.java
    Item murderWeapon = new Item("Murder Weapon", "This is a broken bottle"
            + " with sharp edges and blood covering the edges", true, true);
    
    bar.addItemsToRoom(murderWeapon);
    
    Item bloodSplatter = new Item("Blood splatter", "the ground is covered" +
            " in blood", true, false);
    
    Item gun = new Item("Gun", "Its a smith and wesson, your best friend",
            false, true);
    
    Item corpse = new Item("Corpse", "its a dead guy, he looks to be stabbed" +
            " brutally multiple times.\n When you look closer you notice" +
            " his face is covered in spit", true, false);
    
    Item bloodSplatteredBadge = new Item("Blood Splattered Badge", "its your" +
            " former partners badge covered in blood, odd that you would find" +
            " this here. \n i wave of guilt washes over you as you realise" +
            " what you have done", true, true);
}

private String talk() {
    
    //Gives the player a list of NPCs in the room
    System.out.println("Who do you wish to talk to?");
    for (NPC npc: currentRoom.getNpcsInRoom()) {
        System.out.println(npc.getName());
    }
    //have the player enter a name
    Scanner talking = new Scanner(System.in);
    String target = talking.nextLine();
    
    //go through NPCs for matches to the input.
    for (NPC npc: currentRoom.getNpcsInRoom()) {
        if (target == npc.getName()){
            npc.getDialogue();
            break;
        }
    }
    //if no matches are found, print this line:
    return "There is no one here by that name.";
}
//todo the search is a mess
//add to cluelist
private void search(){
    
    //prints all items in the room.
    if (currentRoom.getItemsInRoom().size()!= 0){
        System.out.println("You found these items:");
        for (Item thing:currentRoom.getItemsInRoom()){
            System.out.println(thing.getName());
        }
        System.out.println("What do you want to pick up?");
        System.out.println("If you don't want anything, type \"nothing\".");
        
        //get an input for the desired item.
        Scanner pick = new Scanner(System.in);
        String newItem = pick.nextLine();
        if (newItem.equals("nothing")){
            System.out.println("You decide that scavenging is above you.");
        }
        else{
            //searches for the item
            boolean success = false;
            for (Item thing:currentRoom.getItemsInRoom()){
                if(newItem == thing.getName()){
                    success = true;
                    player.addToInventory(thing);
                    break;
                }
            }
            if (success=false){
                System.out.println("You can't find that.");
            }
        }
    }
    else {
        System.out.println("You can't find anything.");
    }
}

public void accuse(){
    System.out.println("You do not have the authority to do this.");
}
private void getInfo(){
    
    if (currentRoom.getNpcsInRoom().size()==0){
        System.out.println("You are all alone.");
    }
    else {
        System.out.println("The other people here are:");
        for (NPC npc: currentRoom.getNpcsInRoom()){
            System.out.println(npc.getName());
        }
    }   
}
}
