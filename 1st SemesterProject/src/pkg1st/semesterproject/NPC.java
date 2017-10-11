/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;
import java.util.*;

/**
 *
 * @author Krongrah
 */
public class NPC {
    static String name;
    static List<String> dialougeList = new ArrayList<String>();
    static int suspectability;
    static int dialougeStep;
    
    public static void move(String Room) {
        
    }
    public static String getName(){
        return name;
    }
    public static int getSuspectability() {
        return suspectability;
    } 
    public static int getDialougeStep() {
        return dialougeStep;
    }
    public static List<String> getDialougeList() {
        return dialougeList;
    }
    public static void incDialougeStep() {
        dialougeStep += 1;
    }
    public NPC(String newName, List<String> newDialougeList) {
        this.name = newName;
        this.dialougeList = newDialougeList;
    }
}
