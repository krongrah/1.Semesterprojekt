/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PermanentFileClasses;

/**
 *
 * @author Krongrah
 */
public class Score {
    
private String name;
private int score;
    
Score(String name, int score){
this.name=name;
this.score=score;
}  
public String getName(){
return name;
}
public int getScore(){
return score;
}



}
