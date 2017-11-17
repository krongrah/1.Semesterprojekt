/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IBackEnd;
import Acquaintance.IFoundation;

/**
 *
 * @author Krongrah
 */
public class BackEndFacade implements IBackEnd{

    IFoundation foundation;
    Game game=new Game();

    @Override
    public void injectFoundation(IFoundation foundation) {
        this.foundation=foundation;
    }
    
    public void play(){
        game.getHiScoreManager().pull(foundation);
        game.play();
    
    }    
    
    public String test(){
    return game.test();
    }
}
