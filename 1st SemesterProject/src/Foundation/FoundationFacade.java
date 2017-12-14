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
    SaveFile saveFile= new SaveFile();


    @Override
    public void saveHiScoreList(ArrayList<String> list) {
        hiscore.writeHiScore(list);
    }

    @Override
    public ArrayList<String> getHiScoreList() {
    return hiscore.pullHiScore();
        }
    
  
  public Object save(Object gameState)  {
    return saveFile.saveGame(gameState);
  }
  
    @Override
  public Object getSavedGame()  {
    return saveFile.getSavedGame();
  }
}

  
