/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IBackEnd;
import Acquaintance.IFoundation;
import java.util.Set;

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

    public Set<String> talkMenu(){
    return game.talkMenu();
    }
    
    public void talk(String string){
    game.talk(string);
    }
    
    public void search(){
    game.search();
    }
    
    public void inspect(){
    //game.inspect();
    //todo
    }
    
    public void convict(){
    game.convict();
    }
    
    public void arrest(){
    game.arrest();
    }
    
    public void drink(){
    game.drink();
    }
    
    public void save(){
    //game.save();
    //todo
    }
    
}   