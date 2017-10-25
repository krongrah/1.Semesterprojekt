/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public class PC {

    //player attributes
    private int points;
    private Set<Item> inventory = new HashSet<>();
    private Set<Item> desk = new HashSet<>();
    private Set<Clue> journal = new HashSet<>();
    private int maxInventoryCapacity = 2;
    private Game newGame;
    private Room pd;

    //constructor
    PC(Game game, Room room) {
        points = 100;
        newGame = game;
        pd = room;
    }

    //Checking methods
    public void displayInventory() {
        int empty = 0;
        for (Item thing : inventory) {
            System.out.println(thing.getName()+ ":");
            System.out.println(thing.getDescription()+"\n");            
        }
      
        System.out.println("You have " + inventory.size() + "/" + maxInventoryCapacity + " spots filled in your inventory.");
    }
    public void displayJournal() {
        int empty = 0;
        for (Clue ClueItem : journal) {
            System.out.println(ClueItem.getName()+":");
            System.out.println(ClueItem.getDescription()+"\n");
        }

        if (journal.size() == empty) {
            System.out.println("Your journal is empty");

        }

    } 
    public void checkDesk(Room currentRoom) {
        if (currentRoom == pd) {
            for (Item thing : desk) {
                System.out.println(thing.getName());
            }
        } else {
            System.out.println("You can't check your desk from here.");
        }
    }
   
    //getters for the descriptions
    public void inspectItem(Item thing) {
        if (inventory.contains(thing)) {
            System.out.println(thing.getDescription());
        } else {
            System.out.println("You can't find it in your bag.");
        }
    }

    public void inspectClue(Clue thing) {
        if (journal.contains(thing)) {
            System.out.println(thing.getDescription());
        } else {
            System.out.println("You don't know about that yet.");
        }
    }

    //methods for moving items between inventory and desk
    public void moveToInventory(Item thing) {
        if (inventory.size() <= maxInventoryCapacity) {
            inventory.add(thing);
            desk.remove(thing);
            System.out.println("You sucessfully moved the item from there to here.");
        } else {
            System.out.println("Your bag is too small, or you just don't want to carry it.");
        }
    }

    public void moveToDesk(Item thing) {
        inventory.remove(thing);
        desk.add(thing);
        System.out.println("You sucessfully moved the item from here to there.");
    }

    //methods for adding to cluelist and inventory
    public void addToInventory(Item thing, Room currentRoom) {
        if (thing.getCollectible()) {
            System.out.println("You placed it in your bag.");
            //todo
            currentRoom.getItemsInRoom().remove(thing);
            inventory.add(thing);
        } else {
            System.out.println("You can't seem to get a hold of it.");
        }

    }

    public void addToCluelist(Clue thing) {
        journal.add(thing);
        System.out.println("You noted the clue down.");
        
        
    }
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int value) {
        points += value;
        
    }
    public void removePoints(int value) {
        points -= value;
    }
}

//10 clues
//1 end statement
//accuse
//start at 50, get 10 points per clue, up to 100 , then get 
