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
        System.out.print(name +": ");
        dialogue.getLine();
        System.out.println("\n");
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
    public void fulfillCondition(){
    dialogue.fulfillCondition();
    askForClueCounter=0;
    }

    /**
     * Gets clueCount
     * @return askForClueCounter
     */
    public int getClueCount() {
        askForClueCounter++;    
        return askForClueCounter;
    }
    
    public boolean getClue(){
    askForClueCounter++;
    if(askForClueCounter==clueRelease&& dialogue.getCondition()){
        System.out.println("got clue");
    return true;
    }
        System.out.println(askForClueCounter);
    return false;
    }
    /**
    * gets when the clue is released
    * @return clueRealse
    */
    public int getClueRelease() {
        if(dialogue.getCondition()){
        return clueRelease;
        }
        return -1;
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
