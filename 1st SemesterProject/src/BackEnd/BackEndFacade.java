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
    
    @Override
    public void injectFoundation(IFoundation foundation) {
        this.foundation=foundation;
    }
    
    public void play(){
    Game game=new Game();
    game.play();
    }
    
}
