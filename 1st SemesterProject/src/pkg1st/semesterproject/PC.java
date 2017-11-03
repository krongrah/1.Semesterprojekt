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
     int drunkness;
    private int points;
    private Set<Item> inventory = new HashSet<>();
    private Set<Item> desk = new HashSet<>();
    private Set<Clue> journal = new HashSet<>();
    private Set<Clue> evidence = new HashSet<>(); 
    private int maxInventoryCapacity = 2;
    private Game newGame;
    private Room pd;
    private int currentHealth=100;

    //constructor
    PC(Game game, Room room) {
        points = 50;
        drunkness = 100;
        newGame = game;
        pd = room;
    }

    //Checking methods
    public void displayInventory() {
        int empty = 0;
        for (Item thing : inventory) {
            System.out.println(thing.getName()+ ":");
            System.out.println(thing.getDescription()+"\n");
            System.out.println("you have " + inventory.size() + "/" + maxInventoryCapacity + " spots left in your inventory");
        }

        if (inventory.size() == empty) {
            System.out.println("Your inventory is empty");

        }

        }
    
    public Set<Item> getInventory(){
    return inventory;
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
    public void diplayDesk(Room currentRoom) {
        int empty = 0;
        if (currentRoom == pd) {
            for (Item deskItem : desk) {
            System.out.println(deskItem.getName()+":");
            System.out.println(deskItem.getDescription()+"\n");
        }

        if (desk.size() == empty) {
            System.out.println("Your desk is empty");

        }
        } else {
            System.out.println("You can't check your desk from here, only in the Police station.");
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
        System.out.println("You sucessfully moved the item from your inventory to your desk.");
    }
    public void moveToRoom(Item thing, Room currentRoom) {
        inventory.remove(thing);
        currentRoom.getItemsInRoom().add(thing);
        System.out.println("You sucessfully moved the item from your inventory to "+ currentRoom.getRoomName()+".");
    }

    //methods for adding to cluelist and inventory
    public void addToInventory(Item thing, Room currentRoom) {
        
        if(inventory.size()<maxInventoryCapacity){
            if (thing.getCollectible()) {
            System.out.println("You placed it in your bag.");
            //todo
            currentRoom.getItemsInRoom().remove(thing);
            inventory.add(thing);
             }  else {
            System.out.println("You can't seem to get a hold of it.");
            }
        }
        else {
            System.out.println("Your inventory are full");
        }
        
    }

    public void addToJournal(Clue thing) {
        journal.add(thing);
        System.out.println("You noted the clue down.");
        addPoints(10);
    }
    
    public void addToevidence(Clue thing) {
        journal.add(thing);
        System.out.println("You noted the clue down.");
        addPoints(10);
        }
    
    public boolean isEvidence2(){
    
    return evidence.size()>=2;
    }
    
    
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int value) {
        points += value;
        
    }
    
    public Set<Clue> getJournal(){
        
    return journal;
    }
    
    public Set<Clue> getEvidence(){
    
    return evidence;
    }
    
    public void removePoints(int value) {
        points -= value;
    }
    public void setCurrentHealth(int hp){
    currentHealth=hp;
    }
    public int getCurrentHealth(){
    return currentHealth;
    }
    public boolean inventoryContains(String name){
    for (Item thing: inventory){
    if (thing.getName().equalsIgnoreCase(name)){
    return true;
    }
    }
    return false;
    }
    
    public int getDrunkness(){
        return drunkness;
    }
    
    public void removeDrunkness(int drunkValue){
        drunkness -= drunkValue;
    }
    
    public void addDrunkness(int drunkvalue){
        drunkness += drunkvalue;
    }


}
