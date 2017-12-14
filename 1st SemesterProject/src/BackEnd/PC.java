/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import BackEnd.WorldFill.Room;
import BackEnd.WorldFill.Clue;
import BackEnd.WorldFill.Item;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public class PC implements Serializable{

    //player attributes
    private int minutes=0;
    private int hours=12;
    private int movementChecker=0;
    private int drunkenness=99;
    private int points=90;
    private Map<String,Item> inventory=new HashMap<>();
    private Map<String,Clue> journal = new HashMap<>();
    private Map<String,Clue> evidence = new HashMap<>();
    private int maxInventoryCapacity = 2;
    private int currentHealth = 100;
    private int damage=10;
    private Room currentRoom, lastRoom;
    private String name;
    private Item itemToBePickedUp;
    //constructor
    PC(){}

    public void setPC(PC player) {
        this.currentRoom = player.getRoom();
        this.lastRoom = player.getLastRoom();
        this.name = player.getName();
        this.minutes = player.getMinutes();
        this.hours = player.getHours();
        this.drunkenness= player.getDrunkenness();
        this.inventory = player.getInventory();
        this.points = player.getPoints();
        this.currentHealth= player.getCurrentHealth();
        this.inventory= player.getInventory();
        this.journal = player.getJournal();
        this.evidence= player.getEvidence();
    }
     

    public Map<String,Item>getInventory(){
    return inventory;
    }

    //getters for the descriptions

    public void inspectItemMap(String entry){
        System.out.println(inventory.get(entry).getDescription());
    }
    public void inspectEntryMap(String item){
        System.out.println(journal.get(item).getDescription());
    }
    public String getName(){
    return name;
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
            return("Your inventory is full");
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
        minutes += timer;
    }
    public int getMovementChecker() {
        return movementChecker;
    }
    public void move(Room newRoom){
    lastRoom=currentRoom;
    currentRoom=newRoom;
    }
    
    public void moveBack(){
    currentRoom=getLastRoom();
    }
    Room getRoom(){
    return currentRoom;
    }
    public void setName(String string){
    name=string;
    }
    public String getTime(){
        return (String.format("%02d", getHours())+":"+String.format("%02d", minutes));
    }
    public int returnHours(){
        return getHours();
    }
    public void setHour(int newHour){
        hours = newHour;
    }
    public int getMinutes(){
        return minutes;
    }
    public void setMinutes(int newMinutes){
        minutes = newMinutes;
    }
    public void setDamage(int damage){
    this.damage=damage;
    }
    public int getDamage(){
    return damage;
    }
    Item getItemToBePickedUp(){
        Item holder=itemToBePickedUp;
        itemToBePickedUp=null;
        return holder;
    }
    void setItemToBePickedUp(Item itemToBePickedUp){
    this.itemToBePickedUp=itemToBePickedUp;
    }

    /**
     * @return the lastRoom
     */
    public Room getLastRoom() {
        return lastRoom;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }
    
}
    
    

