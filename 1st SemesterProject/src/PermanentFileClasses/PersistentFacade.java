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
public class PersistentFacade {

    HiScore hiscore=new HiScore();
    SaveFile save=new SaveFile();
    
    public String[] applyForHiScore(String entry){
    return hiscore.doHiScore(entry);
    }
    

    
    

}
