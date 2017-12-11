/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IFoundation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Krongrah
 */
public class HiScoreManager {
    List<String> goodHiScore=new ArrayList<>();
    List<String> badHiScore=new ArrayList<>();
    List<String> tempList=new ArrayList<>();
    IFoundation foundation;
    int hiScoreMax=5;
    
    
    void store(){
    ArrayList<String> combinedList=new ArrayList<>();
    for(String string1:goodHiScore){
    combinedList.add(string1);
    for(String string2:badHiScore){
    combinedList.add(string2);
    foundation.saveHiScoreList(combinedList);
    }}}
    

   
    void retrieve(){
    //retrive the combined list and split it into good and bad.
        ArrayList<String>list=foundation.getHiScoreList();
        for(int i=0;i<hiScoreMax;i++){
        goodHiScore.add(list.get(i));
        }
        for(int j=hiScoreMax;j<2*hiScoreMax;j++){
        goodHiScore.add(list.get(j));
        }
        
        
    }
    
    
    void pull(IFoundation foundation){
    this.foundation=foundation;
    }
    
    void addScore(String name, int points){
        String pointString =String.format("%03d", points);
        if(points<=100){
    badHiScore.add(pointString+" "+name);
    }else{
    goodHiScore.add(pointString+" "+name);
            }
    sort();
    }

    void sort(){
//    //sort both lists
//    
//    
//    
//    
//    Collections.sort(hiScore);
//    
//        //moves all but the first 5 Strings to tempList
//    Iterator<String> iterator=hiScore.iterator();
//    int counter=0;
//    while(iterator.hasNext()){
//    if(counter>4){
//    tempList.add(iterator.next());
//    iterator.remove();
//    }
//    counter++;
//    }
//    
//            //sorts tempList
//        Collections.sort(tempList);
//        
//        //deletes all but the first 5 strings
//    Iterator<String> tempIterator=tempList.iterator();
//    int tempCounter=0;
//    while(tempIterator.hasNext()){
//    if(tempCounter>4){
//    tempIterator.remove();
//    }
//    tempCounter++;
//    }    
//        //moves the content from the tempList to the mainList
//        for(String string:tempList){
//        hiScore.add(string);
//        }
//
    //todo
    }
   
    
}
