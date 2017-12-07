/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import BackEnd.WorldFill.Room;
import BackEnd.WorldFill.Clue;
import BackEnd.WorldFill.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public class PC {

    //player attributes
    private int movementChecker;
    private int drunkenness;
    private int points;
    private Map<String,Item> inventory=new HashMap<>();
    private Map<String,Clue> journal = new HashMap<>();
    private Map<String,Clue> evidence = new HashMap<>();
    private int maxInventoryCapacity = 2;
    private int currentHealth = 100;
    private Room currentRoom, lastRoom;

    //constructor
    PC(){
        points = 90;
        drunkenness = 100;
        movementChecker = 0;
    }

    //Checking methods
    public void displayInventory() {
        System.out.println(inventory.keySet());
    }
    public Set<String> displayInventoryMap(){
    return inventory.keySet();
    }

    public Map<String,Item>getInventoryMap(){
    return inventory;
    }

    public Set<String> displayJournal() {
        return journal.keySet();
        }

    //getters for the descriptions

    public void inspectItemMap(String entry){
        System.out.println(inventory.get(entry).getDescription());
    }
    public void inspectEntryMap(String item){
        System.out.println(journal.get(item).getDescription());
    }

    public void moveToRoom(Item thing, Room currentRoom) {
        inventory.remove(thing.getName());
        currentRoom.addItemToRoom(thing);
    }

    //methods for adding to cluelist and inventory
    public String addToInventory(Item thing, Room currentRoom) {

        if (inventory.size() < maxInventoryCapacity) {
            if (thing.getCollectible()) {
                
                currentRoom.removeItemFromRoomMap(thing.getName());
                inventory.put(thing.getName(),thing);
                return("You placed it in your bag.");
            } else {
                return("You can't seem to get a hold of it.");
            }
        } else {
            return("Your inventory are full");
        }

    }

    public void addToJournal(Clue thing) {
        journal.put(thing.getName(),thing);
        addPoints(5);
    }

    public void addToevidence(String thing) {
        evidence.put(thing,journal.get(thing));
        journal.remove(thing);
    }

    public boolean isEvidence2() {

        return evidence.size() >= 2;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int value) {
        points += value;

    }

    public Map<String, Clue> getJournal() {

        return journal;
    }

    public Map<String,Clue> getEvidence() {

        return evidence;
    }

    public void removePoints(int value) {
        points -= value;
    }

    public void setCurrentHealth(int hp) {
        currentHealth = hp;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean inventoryContains(String name) {
        return inventory.containsKey(name);
    }

    public int getDrunkenness() {
        return drunkenness;
    }

    public void removeDrunkenness(int drunkValue) {
        drunkenness -= drunkValue;
    }

    public void addDrunkenness(int drunkvalue) {
        drunkenness += drunkvalue;
    } 
    public void passTime(int timer){
        movementChecker += timer;
    }
    public int getMovementChecker() {
        return movementChecker;
    }
    public void move(Room newRoom){
    lastRoom=currentRoom;
    currentRoom=newRoom;
    }
    
    public void moveBack(){
    currentRoom=lastRoom;
    }
    Room getRoom(){
    return currentRoom;
    }
}
    
    

