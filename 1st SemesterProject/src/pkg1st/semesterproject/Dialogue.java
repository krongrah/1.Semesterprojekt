/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

/**
 *
 * @author Krongrah
 */
public class Dialogue {
    
    //Attributes
    private String[] dialogue1;
    private boolean condition1=false;
    private String[] dialogue2=null;
    private int lineIndex=0;
    
    //single dialogue constructor
    Dialogue(String[] dialogue1){
    this.dialogue1=dialogue1;
    }
    //double dialogue constructor
    Dialogue(String[] dialogue1, String[] dialogue2){
    this.dialogue1=dialogue1;
    this.dialogue2=dialogue2;
    }
    
    public void getLine(){
        System.out.println("test");
        if(!condition1){
        System.out.println(dialogue1[lineIndex]);
        if (lineIndex<dialogue1.length){
        lineIndex++;
        }
    } else {
        System.out.println(dialogue2[lineIndex]);
        if (lineIndex<dialogue2.length){
        lineIndex++;
        }
        }
    }
    
    public void fulfillCondition(){
    condition1=true;
    lineIndex=0;
    }
}
