package BackEnd.WorldFill;



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
    

    /**
     * 
     * @param description is the description of the room. Example: " in the bar"
     * @param roomName is the name of the room Example: "Bar"
     */
    public Room(String description, String roomName) {
        this.description = description;
        this.roomName = roomName;
        exits = new HashMap<String, Room>();
        this.hoboAccessable=false;
    }
    /**
     * 
     * @param description is the description of the room. Example: " in the bar"
     * @param roomName is the name of the room Example: "Bar"
     * @param hoboAccessable is used to define if hobos can walk in this room.
     */
    public Room(String description, String roomName, boolean hoboAccessable) {
        this.description = description;
        this.roomName = roomName;
        exits = new HashMap<String, Room>();
        this.hoboAccessable=hoboAccessable;
    }
/**
 * adds a neighbouring room to the Exits map, with the direction being the key
 * @param direction The direction to the neighbor room. 
 * @param neighbor The neighbouring room you want to access when write "go [direction]"
 * Example of use:  bar.setExit("north", leftStreet);
 */    
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }
/**
 * 
 * @return description of the room.

 */
    public String getShortDescription() {
        return description;
    }

    /**
     * @return the ItemsInRoom Set
     */
    public Set<Item> getItemsInRoom() {
        return ItemsInRoom;
    }

    /** Adds items to the ItemsInRoom Set.
     * @param item you want to add to ItemsInRoom Set.
     * Example of use: bar.addItemsToRoom(beer);
     */
    public void addItemsToRoom(Item item) {
        this.ItemsInRoom.add(item);
    }
    /**
    * Removes items from room set
    * @param item you want to be removed from itemsInRoom Set.
    * Example of use: bar.removeItemFromRoom(beer);
    */ 
    public void removeItemFromRoom(Item item) {
        this.ItemsInRoom.remove(item);
    }
    /**
     * @return the npcsInRoom Set.
     */
    public Set<NPC> getNPCsInRoom() {
        return NPCsInRoom;
    }
    /**
    * 
    * @return the NPCsInRoomMap
    */
    public Map<String, NPC> getNPCsInRoomMap() {
        return NPCsInRoomMap;
    }

    /**Adds NPC to the NPCsInRoom Set
     * @param npc is the NPC you want added to the room.
     * Example of use: bar.addNpcToRoom(bartender);
     */
    public void addNpcToRoom(NPC npc) {
        this.NPCsInRoom.add(npc);
        this.NPCsInRoomMap.put(npc.getName(), npc);
        npc.setCurrentRoomName(this.roomName);          
    }

    /**Removes NPC from the NPCsInRoom Set.
     * @param npc is the npc you want removed from the room
     * Example of use: bar.removeNpcFromRoom(bartender)
     */
    public void removeNpcFromRoom(NPC npc) {
        //this.NPCsInRoom.remove(npc);
        this.NPCsInRoomMap.remove(npc.getName());
        npc.setCurrentRoomName(null);
    }
    
    /**
     * Moves NPCs from one room to another.
     * @param npc is the NPC in the room you want moved
     * @param newRoom is the room you want the NPC to be moved to.
     * Example of use: bar.moveNpc(bartender, leftstreet);
     */
    
    public void moveNpc(NPC npc, Room newRoom){
        
        if(this.getNPCsInRoom().contains(npc)){
        this.NPCsInRoom.remove(npc);
        this.NPCsInRoomMap.remove(npc.getName());
        newRoom.NPCsInRoom.add(npc);
        newRoom.NPCsInRoomMap.put(npc.getName(), npc);
        npc.setCurrentRoomName(this.roomName);
        }
    }
/**
 * 
 * @return returns the exits of a certain room.  Print out example:
 * You are in the bar. [description]
*  Exits:| north = Left Street | [getExitString()]

 */
    public String getLongDescription() {
        return "You are" + description + ".\n" + getExitString();
    }
   

    
    /**
     * 
     * @return returns Exits of the current room. Print out example:
     *  Exits:| north = Left Street |
     */
    private String getExitString() {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for (String exit : keys) {
            returnString += "| " + exit + " = " + exits.get(exit).getRoomName() + " |";
        }
        return returnString;
    }
/**
 * 
 * @param direction
 * @return returns the room in the given direction.
 */
    //
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * @return the name instance varible of the room.
     */
    public String getRoomName() {
        return roomName;
    }
    
    /**
     * 
     * @return Returns the fighter the player might fight.
     * this method finds a random number between 0 and 1, if the number is lower than
     * HostileNPC agression, then you fight. 
     */
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
     * @return instance varible HoboAccessable, used to if a hobo can walk into a room.
     */
    public boolean isHoboAccessable() {
        return hoboAccessable;
    }

}
