/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

/**
 *
 * @author Krongrah
 */
public class Dialogue implements Serializable{

    //Attributes
    private String[] dialogue1=null;
    private boolean condition = false;
    private String[] dialogue2;
    private String alibi;
    private String fightScream;
    private boolean isValid;
    private int lineIndex = 0;

    /**
     * Constructor for Dialogue with one set of Strings
     * @param dialogue2 The Strings the NPC gives when talked to
     * @param alibi The String given when the NPC is arrested
     * @param isValid Is true if the the alibi is valid, else false.
     */
    public Dialogue(String[] dialogue2, String alibi, boolean isValid) {
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
        condition=true;
    }

    /**
     * Constructor for Dialogue with two sets of Strings
     * @param dialogue1 The Strings the NPC gives when talked to before their condition has been fulfilled
     * @param dialogue2 The Strings the NPC gives when talked to after their condition has been fulfilled
     * @param alibi The String given when the NPC is arrested
     * @param isValid Is true if the the alibi is valid, else false.
     */
    public Dialogue(String[] dialogue1, String[] dialogue2, String alibi, boolean isValid) {
        this.dialogue1 = dialogue1;
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
    }
    
    /**
     * Constructor for Dialogue of a hostile NPC
     * dialogue2 The Strings the hostile NPC gives when talked to
     * @param dialogue2 The Strings the NPC gives when talked to
     * @param alibi The String given when the hostile NPC is arrested
     * @param isValid Is true if the the alibi is valid, else false.
     @param fightScream The fightScream of the hostile NPC
     */
    public Dialogue(String[] dialogue2, String alibi, boolean isValid, String fightScream) {
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
        this.fightScream = fightScream;
        this.condition=true;
    }

    /**
     * Prints the next line of dialogue
     */
    public void getLine() {
        if (!condition) {
            System.out.println(dialogue1[lineIndex]);
            if (lineIndex < dialogue1.length - 1) {
                lineIndex++;
            }
        } else {
            System.out.println(dialogue2[lineIndex]);
            if (lineIndex < dialogue2.length - 1) {
                lineIndex++;
            }
        }
    }

    /**
     * If the condition is false, sets it to true and resets lineIndex to 0.
     */
    void fulfillCondition() {
        if(!condition){
        condition = true;
        lineIndex = 0;
        }}

    /**
     * 
     * @return Returns the alibi if it is valid, else prints the alibi and returns null
     */
    String getAlibi() {
        if(isValid){
        return alibi;
        }else{
        System.out.println(alibi);
        return null;
        }
    }
    
    /**
     * 
     * @return Returns the fightScream
     */
    public String getFightScream() {
        return fightScream;
    }
    
    /**
     * 
     * @return Returns the condition
     */
    public boolean getCondition(){
    return condition;
    }

}
