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
public class Dialogue {

    //Attributes
    private String[] dialogue1=null;
    private boolean condition = false;
    private String[] dialogue2;
    private String alibi;
    private String fightScream;
    private boolean isValid;
    private int lineIndex = 0;

    //single dialogue constructor
    public Dialogue(String[] dialogue2, String alibi, boolean isValid) {
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
        condition=true;

    }

    //double dialogue constructor
    public Dialogue(String[] dialogue1, String[] dialogue2, String alibi, boolean isValid) {
        this.dialogue1 = dialogue1;
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
    }
    public Dialogue(String[] dialogue2, String alibi, boolean isValid, String fightScream) {
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
        this.fightScream = fightScream;
        this.condition=true;
    }

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

    void fulfillCondition() {
        if(!condition){
        condition = true;
        lineIndex = 0;
        }}

    public String getAlibi() {
        if(isValid){
        return alibi;
        }else{
        System.out.println(alibi);
        return null;
        }
    }
    
    /**
     * Prints the fight scream.
     * @return fightScream
     */
    public String getFightScream() {
        return fightScream;
    }
    public boolean getCondition(){
    return condition;
    }

}
