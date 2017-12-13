/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Krongrah
 */
public interface IFoundation {
   void saveHiScoreList(ArrayList<String> list);
   ArrayList<String> getHiScoreList();
   boolean save(List aList);
   List load();
   boolean saveMap(HashMap aMap);
   HashMap loadMap();
}