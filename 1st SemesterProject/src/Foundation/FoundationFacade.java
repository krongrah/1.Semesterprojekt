/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import Acquaintance.IFoundationFacade;
import java.util.ArrayList;

/**
 *
 * @author Krongrah
 */
public class FoundationFacade implements IFoundationFacade{

    private HiScore hiscore=new HiScore();
    private SaveFile save=new SaveFile();
    


    @Override
    public void saveHiScoreList(ArrayList<String> list) {
        hiscore.writeHiScore(list);
    }

    @Override
    public ArrayList<String> getHiScoreList() {
return hiscore.pullHiScore();
        }
    

    
    

}
