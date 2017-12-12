package BackEnd.WorldFill;




import java.util.Set;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room {   //rooms have a description/name and map with strings to rooms, serving as exits.

    private String description;
    private HashMap<String, Room> exits;
    private Map<String,Item> ItemsInRoom = new HashMap<>();
    private Map<String, NPC> NPCsInRoom = new HashMap<>();
    private String roomName;
    private boolean hoboAccessable;
    

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
    
    public Map<String,Item> getItemsInRoomMap() {
        return ItemsInRoom;
    }
    

    /** Adds items to the ItemsInRoom Map.
     * @param item you want to add to ItemsInRoom Map.
     * Example of use: bar.addItemToRoom(beer);
     */
    public void addItemToRoom(Item item) {
        this.ItemsInRoom.put(item.getName(), item);
    }

    
    /**
    * Removes items from room set
    * @param string is the item name you want to be removed from itemsInRoom Set.
    * Example of use: bar.removeItemFromRoom(beer);
    */ 
    public void removeItemFromRoomMap(String string){
        this.ItemsInRoom.remove(string);
    }

    /**
    * 
    * @return the NPCsInRoomMap
    */
    public Map<String, NPC> getNPCsInRoomMap() {
        return NPCsInRoom;
    }

    /**Adds NPC to the NPCsInRoom Set
     * @param npc is the NPC you want added to the room.
     * Example of use: bar.addNpcToRoom(bartender);
     */
    public void addNpcToRoom(NPC npc) {
        this.NPCsInRoom.put(npc.getName(), npc);
        npc.setCurrentRoomName(this.roomName);          
    }

    /**Removes NPC from the NPCsInRoom Set.
     * @param npc is the npc you want removed from the room
     * Example of use: bar.removeNpcFromRoom(bartender)
     */
    public void removeNpcFromRoom(NPC npc) {
        //this.NPCsInRoom.remove(npc);
        this.NPCsInRoom.remove(npc.getName());
        npc.setCurrentRoomName(null);
    }
    
    /**
     * Moves NPCs from one room to another.
     * @param npc is the NPC in the room you want moved
     * @param newRoom is the room you want the NPC to be moved to.
     * Example of use: bar.moveNpc(bartender, leftstreet);
     */
    
    public void moveNpc(NPC npc, Room newRoom){
        
        if(this.getNPCsInRoomMap().containsKey(npc.getName())){
        this.NPCsInRoom.remove(npc.getName());
        newRoom.NPCsInRoom.put(npc.getName(), npc);
        npc.setCurrentRoomName(newRoom.getRoomName());
        }
    }
/**
 * 
 * @return returns the exits of a certain room.  Print out example:
 * You are in the bar. [description]
*  Exits:| north = Left Street | [getExitString()]

 */
    public String getLongDescription() {
        return "You are" + description;
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
     * @return the name instance variable of the room.
     */
    public String getRoomName() {
        return roomName;
    }
    


    /**
     * @return instance variable HoboAccessable, used to if a hobo can walk into a room.
     */
    public boolean isHoboAccessable() {
        return hoboAccessable;
    }
    
    public HashMap getExit(){
        return exits;
    }

}
