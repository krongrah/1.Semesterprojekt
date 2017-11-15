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
    private String[] dialogue1;
    private boolean condition1 = false;
    private String[] dialogue2 = null;
    private String alibi;
    private String fightScream;
    private boolean isValid;
    private int lineIndex = 0;

    //single dialogue constructor
    public Dialogue(String[] dialogue1, String alibi, boolean isValid) {
        this.dialogue1 = dialogue1;
        this.alibi = alibi;
        this.isValid = isValid;

    }

    //double dialogue constructor
    public Dialogue(String[] dialogue1, String[] dialogue2, String alibi, boolean isValid) {
        this.dialogue1 = dialogue1;
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
    }
    public Dialogue(String[] dialogue1, String alibi, boolean isValid, String fightScream) {
        this.dialogue1 = dialogue1;
        this.dialogue2 = dialogue2;
        this.alibi = alibi;
        this.isValid = isValid;
        this.fightScream = fightScream;
    }

    public String getLine() {
        if (!condition1) {
            String line = dialogue1[lineIndex];
            if (lineIndex < dialogue1.length - 1) {
                lineIndex++;

            }
            return line;
        } else {
            String line = dialogue2[lineIndex];
            if (lineIndex < dialogue2.length - 1) {
                lineIndex++;

            }
            return line;
        }

    }

    public void fulfillCondition() {
        condition1 = true;
        lineIndex = 0;
    }

    public boolean getAlibi() {
        System.out.println(alibi);
        return isValid;
    }

    /**
     * @return the fightScream
     */
    public String getFightScream() {
        return fightScream;
    }

}
