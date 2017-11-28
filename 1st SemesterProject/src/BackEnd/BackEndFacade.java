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
    
    @Override
    public void play(){
        game.getHiScoreManager().pull(foundation);
        game.play();
    
    }    


    
    @Override
    public void talk(String string){
    game.talk(string);
    }
    
    @Override
    public boolean search(String string){
    return game.search(string);
    }
    
    @Override
    public void inspect(String string){
    //game.inspect(string);
    //todo
    }
    
    @Override
    public void convict(String string){
    game.convict(string);
    }
    
    @Override
    public void arrest(String string){
    game.arrest(string);
    }
    
    @Override
    public void drink(){
    game.drink();
    }
    
    @Override
    public void save(){
    //game.save();
    //todo
    }

    @Override
    public Set<String> talkMenu(){
    return game.talkMenu();
    }
    
    @Override
    public Set<String> arrestMenu() {
        return game.arrestMenu();
    }

    @Override
    public Set<String> convictMenu() {
        return game.convictMenu();
    }

    @Override
    public Set<String> searchMenu() {
        return game.searchMenu();
    }

    @Override
    public Set<String> inspectMenu() {
        return game.inspectMenu();
    }
    
}   