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
        if (game.search(string)){
        return true;
        }
        else{
        return false;
        }
    }
    public void pickUpAsk(String answer){
    game.pickup(answer);
    }
    
    
    @Override
    public Set<String> inspect(String string){
    return game.inspect(string);
    
    }
    
    @Override
    public void convict(String string){
    game.convict(string);
    }
    
    @Override
    public boolean arrest(String string){
    return game.arrest(string);
    }
    
    @Override
    public void drink(){
    game.drink();
    }
    
    @Override
    public void save(){
    game.save();
    
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
    
    @Override
    public String getCurrentRoom(){        
        return game.getPlayer().getRoom().getRoomName();
    }
    
    @Override
    public String[] getExits(){
        String[] exits = new String[game.getPlayer().getRoom().getExit().size()];
        game.getPlayer().getRoom().getExit().keySet().toArray(exits);
     return exits;
    }
    
    @Override
    public void UIGo(String e){
    game.UIGo(e);
    }

    @Override
    public void journal(String string) {
        game.journal(string);
    }

    @Override
    public void inventory(String string) {
        game.inventory(string);
    }
      
}