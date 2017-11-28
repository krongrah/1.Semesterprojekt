/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;


/**
 *
 * @author Krongrah
 */
public class NPC {

    // NPC attributes
    private String name;
    private Dialogue dialogue;
    private Clue clue;
    private int askForClueCounter = 0;
    private int clueRelease;
    private String NPCCurrentRoomName;
    
    /**
     * NPC constructor
     * @param newName Is the name of the HostileNPC
     * @param dialogue The HostileNPC's dialog strings.
     * @param clue the clue given in dialog [If any]
     * @param clueRelease How many times the player has to talk to the NPC before getting a clue.
     */
    public NPC(String newName, Dialogue dialogue, Clue clue, int clueRelease) {
        this.name = newName;
        this.dialogue = dialogue;
        this.clue = clue;
        this.clueRelease = clueRelease;
    }
    
    /**
     * Gets name
     * @return name
     */
    public String getName() {
        return name;
    }
    
/**
 * Prints out a dialog line
 */
    public void getLine() {
        dialogue.getLine();
    }

    /**
     * gets Alibi
     * @return getDialogue().getAlibi()
     */
    public boolean getAlibi() {
        return getDialogue().getAlibi();
    }
    /**
    * Set dialog object.
    * @param dialogue_ The dialogue object you want to bind with the NPC
    */ 
    public void setDialogue(Dialogue dialogue_) { 
        dialogue = dialogue_;
    }

    /**
     * Gets clueCount
     * @return askForClueCounter
     */
    public int getClueCount() {
        askForClueCounter++;    
        return askForClueCounter;
    }
    /**
    * gets when the clue is realsed
    * @return clueRealse
    */
    public int getClueRelease() {
        return clueRelease;
    }
/**
 * gets clue
 * @return clue
 */
    public Clue giveClue() {
        return clue;
    }

    /**
     * @return the dialogue
     */
    public Dialogue getDialogue() {
        return dialogue;
    }

    /**
     * @return the NPCcurrentRoom
     */
    public String getCurrentRoomName() {
        return NPCCurrentRoomName;
    }

    /**
     * @param NPCCurrentRoomName the new room of the NPC
     */
    public void setCurrentRoomName(String NPCCurrentRoomName) {
        this.NPCCurrentRoomName = NPCCurrentRoomName;
    }
   
}
