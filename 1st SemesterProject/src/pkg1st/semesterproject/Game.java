package pkg1st.semesterproject;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
import java.util.Scanner;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
    // Cunstructor calls createRooms and creates new Parser
    public Game() 
    {
        createRooms();
        createNPCs();
        createItems();
        
        parser = new Parser();
    }

    // Creates all rooms and their exits
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theatre.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;
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
        }
        System.out.println("Thank you for playing.  Good bye.");
    }
    
    // Prints the welcome message, the help command, and the current room
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
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
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    //Checks if directions has an exit and moves to next room
    private void goRoom(Command command) 
    {
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
        }
    }
    
    // Quits the game 
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
    private void createNPCs(){
    
    }
    
    private void createItems(){
    
    }
    /*
    private String talk(){
        
        //Gives the player a list of NPCs in the room
        System.out.println("Who do you wish to talk to?");
        for (NPC npc: NPCsInRoom){
            System.out.println(NPCs.getName());
        }
        //have the player enter a name
        Scanner talking=new Scanner(System.in);
        String target=talking.nextLine();
        
        //go through NPCs for matches to the input.
        for (NPC npc: NPCsInRoom){
        if (target==npc){
            int step=npc.getDialogueStep();             //print the next line of dialogue.
            System.out.println(npc.getDialogue[step]);
            npc.incrementDialogueStep();                
            break;
        } 
        }
      //if no matches are found, print this line:
    return "There is no one here by that name.";
    }
    
    private void search(){
        System.out.println("You found these items:");
        for (Item thing:ItemsInRoom){
            System.out.println(thing.getName());
        }
        System.out.println("What do you want to pick up?");
        System.out.println("If you don't want anything, type \"Nothing\".");
        Scanner pick=new Scanner(System.in);
        String newItem=pick.nextLine();
        boolean sucess=false;
        for (Item thing:ItemsInRoom){
            if(newItem==thing.getName){
            sucess=true;
            if(thing.getCollectible){
                System.out.println("You placed it in your bag.");
                ItemsInRoom.remove(thing);
                Inventory.add(thing);
            }else{
                System.out.println("You can't seem to get a hold of it.");
            }    
            }
        }
        if (!sucess){
            System.out.println("You can't find that.");
        };
    }
    */
    private void accuse(){
    
    }
    
    
}
