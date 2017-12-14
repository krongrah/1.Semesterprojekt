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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
class PC implements Serializable {

    //player attributes
    private int minutes = 0;
    private int hours = 12;
    private int drunkenness = 99;
    private int points = 90;
    private Map<String, Item> inventory = new HashMap<>();
    private Map<String, Clue> journal = new HashMap<>();
    private Set<Clue> evidence = new HashSet<>();
    private int maxInventoryCapacity = 2;
    private int currentHealth = 100;
    private int damage = 10;
    private Room currentRoom, lastRoom;
    private String name;
    private Item itemToBePickedUp;

    /**
     * Constructs a PC.
     */
    PC() {
    }

    public void setPC(PC player) {
        this.currentRoom = player.getRoom();
        this.lastRoom = player.getLastRoom();
        this.name = player.getName();
        this.minutes = player.getMinutes();
        this.hours = player.getHours();
        this.drunkenness = player.getDrunkenness();
        this.inventory = player.getInventory();
        this.points = player.getPoints();
        this.currentHealth = player.getCurrentHealth();
        this.inventory = player.getInventory();
        this.journal = player.getJournal();
        this.evidence = player.getEvidence();
    }

    /**
     *
     * @return Returns the Inventory
     */
    public Map<String, Item> getInventory() {
        /**
         *
         * @return Returns the Inventory
         */
        return inventory;
    }

    /**
     * Prints the description of the Clue whose name is the argument.
     *
     * @param entry The Clue whose description will be printed.
     */
    void inspectItem(String entry) {
        System.out.println(inventory.get(entry).getDescription());
    }

    /**
     * Prints the description of the Item whose name is the argument.
     *
     * @param item The Item whose description will be printed.
     */
    void inspectEntry(String item) {
        System.out.println(journal.get(item).getDescription());
    }

    /**
     *
     * @return Returns the name of the player.
     */
    String getName() {
        return name;
    }

    /**
     * Moves the thing argument from inventory to the room argument.
     *
     * @param thing The item to be moved.
     * @param room The room the item is to be moved to.
     */
    void moveToRoom(Item thing, Room room) {
        inventory.remove(thing.getName());
        room.addItemToRoom(thing);
    }

    /**
     * Adds the argument to Inventory, unless the inventory is at max capacity.
     *
     * @param thing The Item being added to the inventory.
     * @return Returns a String with a string to be printed.
     */
    String addToInventory(Item thing) {
        if (inventory.size() < maxInventoryCapacity) {
            currentRoom.removeItemFromRoomMap(thing.getName());
            inventory.put(thing.getName(), thing);
            return ("You placed it in your bag.");
        } else {
            return ("Your inventory is full");
        }
    }

    /**
     * Adds the argument to the journal and awards the player with points.
     *
     * @param thing the Clue being added to the Journal
     */
    void addToJournal(Clue thing) {
        journal.put(thing.getName(), thing);
        addPoints(5);
    }

    /**
     * Moves the Clue from the Journal to Evidence that has the Journal-Key
     * matching the argument.
     *
     * @param clue The name of the Clue to be moved from the Journal to
     * Evidence.
     */
    void addToevidence(String clue) {
        getEvidence().add(journal.get(clue));
        evidence.add(journal.get(clue));
        journal.remove(clue);
    }

    /**
     *
     * @return Returns true if the player has two or more clues in Evidence.
     */
    boolean isSecondEvidence() {

        return getEvidence().size() >= 2;
    }

    /**
     *
     * @return Returns the score of the player.
     */
    int getPoints() {
        return points;
    }

    /**
     * Increases the player's score by the argument.
     *
     * @param value The amount of points the player gets.
     */
    void addPoints(int value) {
        points += value;

    }

    /**
     *
     * @return Returns the journal, containing all of the gathered clues.
     */
    Map<String, Clue> getJournal() {

        return journal;
    }

    /**
     * Lowers the player's score by the argument.
     *
     * @param value The amount of points the player loses.
     */
    void removePoints(int value) {
        points -= value;
    }

    /**
     * Sets currentHealth to the argument.
     *
     * @param hp The new amount of health the player has.
     */
    void setCurrentHealth(int hp) {
        currentHealth = hp;
    }

    /**
     *
     * @return Returns the current health of the player.
     */
    int getCurrentHealth() {
        return currentHealth;
    }

    /**
     *
     * @return Returns the player's drunkenness.
     */
    int getDrunkenness() {
        return drunkenness;
    }

    /**
     * Decreases the player's drunkenness by the drunkValue.
     *
     * @param drunkValue the amount drunkenness should be decreased by.
     */
    void removeDrunkenness(int drunkValue) {
        drunkenness -= drunkValue;
    }

    /**
     * Increases the player's drunkenness by the drunkValue.
     *
     * @param drunkValue the amount drunkenness should be increased by.
     */
    void addDrunkenness(int drunkValue) {
        drunkenness += drunkValue;
    }

    /**
     * Increases the current time by the number of minutes specified by the
     * argument.
     *
     * @param timer The number of the time is to be passed by.
     */
    void passTime(int timer) {
        minutes += timer;
    }

    /**
     * Sets the currentRoom to the argument, and the lastRoom to the past
     * currentRoom.
     *
     * @param newRoom The room the player is moving to.
     */
    void move(Room newRoom) {
        lastRoom = currentRoom;
        currentRoom = newRoom;
    }

    /**
     * Sets currentRoom to lastRoom, effectively moving the player backwards.
     */
    void moveBack() {
        currentRoom = lastRoom;
    }

    /**
     *
     * @return Return the room the player currently inhabits.
     */
    Room getRoom() {
        return currentRoom;
    }

    /**
     * Sets the name of the player.
     *
     * @param name The name of the player
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return Returns the current time as a formatted string.
     */
    String getTimeString(){
        return (String.format("%02d", hours)+":"+String.format("%02d", minutes));

    }

    /**
     *
     * @return Returns the current hours of the the time.
     */
    int returnHours() {
        return hours;

    }

    /**
     * Rets the minutes of the time.
     *
     * @param hours The new value of the minutes
     */
    void setHour(int hours) {
        this.hours = hours;
    }

    /**
     *
     * @return Returns the current minutes of the the time.
     */
    int getMinutes() {
        return minutes;
    }

    /**
     * Sets the minutes of the time.
     *
     * @param minutes The new value of the minutes
     */
    void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Sets the damage of the player to the argument of the method.
     *
     * @param damage The value the player's damage is to be set to.
     */
    void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     *
     * @return Returns the damage of the player.
     */
    int getDamage() {
        return damage;
    }

    /**
     *
     * @return Returns the itemToBePickedUp Item and then sets the Item to null.
     */
    Item getItemToBePickedUp() {
        Item holder = itemToBePickedUp;
        itemToBePickedUp = null;
        return holder;
    }

    /**
     * Sets the itemToBePickedUp variable to the argument if the method.
     *
     * @param itemToBePickedUp The Item to be stored for use in the pickUp
     * method of the game Class
     */
    void setItemToBePickedUp(Item itemToBePickedUp) {
        this.itemToBePickedUp = itemToBePickedUp;
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

    /**
     * @return the evidence
     */
    public Set<Clue> getEvidence() {
        return evidence;
    }
}
