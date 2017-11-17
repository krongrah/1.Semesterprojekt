/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IFoundation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Krongrah
 */
public class HiScoreManager {
    List<String> hiScore=new ArrayList<>();
    List<String> tempList=new ArrayList<>();
    IFoundation foundation;
    
    void store(){
    //todo
    }
    void sort(){
    Collections.sort(hiScore);
    
    //todo
    }
    
    void pull(IFoundation foundation){
    this.foundation=foundation;
    }
    
    void add(String name, int points){
        String pointString =String.format("%03d", points);
    hiScore.add(pointString+" "+name);
    sort();
    }
}
