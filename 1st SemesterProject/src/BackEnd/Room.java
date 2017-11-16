package BackEnd;

import BackEnd.WorldFill.NPC;
import BackEnd.WorldFill.HostileNPC;
import BackEnd.WorldFill.Item;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {   //rooms have a description/name and map with strings to rooms, serving as exits.

    private String description;
    private HashMap<String, Room> exits;
    private Set<Item> ItemsInRoom = new HashSet<>();
    private Set<NPC> NPCsInRoom = new HashSet<>();
    private Map<String, NPC> NPCsInRoomMap = new HashMap<>();
    private String roomName;
    private boolean hoboAccessable;
    

    //contructor, requires description and generates an Exits map.
    public Room(String description, String roomName) {
        this.description = description;
        this.roomName = roomName;
        exits = new HashMap<String, Room>();
        this.hoboAccessable=false;
    }
    public Room(String description, String roomName, boolean isHoboAccessable) {
        this.description = description;
        this.roomName = roomName;
        exits = new HashMap<String, Room>();
        this.hoboAccessable=hoboAccessable;
    }

    //adds a neighbooring room to the Exits map, with the direction being the key
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    //getter for the description/name
    public String getShortDescription() {
        return description;
    }

    /**
     * @return the ItemsInRoom
     */
    public Set<Item> getItemsInRoom() {
        return ItemsInRoom;
    }

    /**
     * @param itemsInRoom the ItemsInRoom to set
     */
    public void addItemsToRoom(Item itemsInRoom) {
        this.ItemsInRoom.add(itemsInRoom);
    }

    public void removeItemFromRoom(Item itemsInRoom) {
        this.ItemsInRoom.remove(itemsInRoom);
    }

    /**
     * @return the npcsInRoom
     */
    public Set<NPC> getNPCsInRoom() {
        return NPCsInRoom;
    }

    public Map<String, NPC> getNPCsInRoomMap() {
        return NPCsInRoomMap;
    }

    /**
     * @param npcsInRoom the npcsInRoom to set
     */
    public void addNpcToRoom(NPC npc) {
        this.NPCsInRoom.add(npc);
        this.NPCsInRoomMap.put(npc.getName(), npc);
    }

    /**
     * @param npcsInRoom the npcsInRoom to set
     */
    public void removeNpcFromRoom(NPC npc) {
        //this.NPCsInRoom.remove(npc);
        this.NPCsInRoomMap.remove(npc.getName());
    }
    
    public void moveNpc(NPC npc, Room newRoom){
        
        if(this.getNPCsInRoom().contains(npc)){
        this.NPCsInRoom.remove(npc);
        this.NPCsInRoomMap.remove(npc.getName());
        newRoom.NPCsInRoom.add(npc);
        newRoom.NPCsInRoomMap.put(npc.getName(), npc);
        }
    }

    //Getter for the description/name along with a list of exits.
    public String getLongDescription() {
        return "You are" + description + ".\n" + getExitString();
    }
    //returns a list of exits

    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += "| " + exit + " = " + exits.get(exit).getRoomName() + " |";
        }
        return returnString;
    }

    //returns the room in the given direction.
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * @return the name
     */
    public String getRoomName() {
        return roomName;
    }

    public HostileNPC getJumped() {
        for (NPC fighter : NPCsInRoom) {
            if (fighter instanceof HostileNPC) {
                if (Math.random()<((HostileNPC) fighter).getAggression()) {
                    return (HostileNPC) fighter;
                }
            }
        }
        return null;
    }

    /**
     * @return the isHoboAccessable
     */
    public boolean HoboAccessable() {
        return hoboAccessable;
    }

}
