package pkg1st.semesterproject;

import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{   //rooms have a description/name and map with strings to rooms, serving as exits.
    private String description;
    private HashMap<String, Room> exits;
    private Set<Item> ItemsInRoom = new HashSet<>();
    private Set<NPC> npcsInRoom = new HashSet<>();
   
        
    //contructor, requires description and generates an Exits map.
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        
    }
    //adds a neighbooring room to the Exits map, with the direction being the key
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }
    //getter for the description/name
    public String getShortDescription()
    {
        return description;
    }  
    
    /**
     * @return the ItemsInRoom
     */
    public Set<Item> getItemsInRoom() {
        return ItemsInRoom;
    }

    /**
     * @param ItemsInRoom the ItemsInRoom to set
     */
    public void setItemsInRoom(Set<Item> ItemsInRoom) {
        this.ItemsInRoom = ItemsInRoom;
    }

    /**
     * @return the npcsInRoom
     */
    public Set<NPC> getNpcsInRoom() {
        return npcsInRoom;
    }

    /**
     * @param npcsInRoom the npcsInRoom to set
     */
    public void setNpcsInRoom(Set<NPC> npcsInRoom) {
        this.npcsInRoom = npcsInRoom;
    }
    //Getter for the description/name along with a list of exits.
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
        //returns a list of exits
	private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    //returns the room in the given direction.
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

