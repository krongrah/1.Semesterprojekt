/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.util.ArrayList;
import Acquaintance.IFoundation;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Krongrah
 */
public class FoundationFacade implements IFoundation{

    private HiScore hiscore=new HiScore();
    private SaveFile save=new SaveFile();
    SaveFile saveFile;


    @Override
    public void saveHiScoreList(ArrayList<String> list) {
        hiscore.writeHiScore(list);
    }

    @Override
    public ArrayList<String> getHiScoreList() {
    return hiscore.pullHiScore();
        }
    
  
  public boolean save(List aList)  {
    return saveFile.saveGame(aList);
  }
  
  public List load()  {
    return saveFile.loadGame();
  }
  
  public boolean saveMap(HashMap aMap){
    return this.saveFile.saveMap(aMap);
  }
  
  public HashMap loadMap(){
    return this.saveFile.loadMap();
  }
    
}
