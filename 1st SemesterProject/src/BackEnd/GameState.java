/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.io.Serializable;

/**
 *
 * @author jensd
 */
public class GameState implements Serializable{
    private BackEnd.PC player;
    private BackEnd.World world;
    
    GameState(PC player, World world){
    this.player=player;
    this.world=world;
              
    }

    /**
     * @return the player
     */
    public BackEnd.PC getPlayer() {
        return player;
    }

    /**
     * @return the world
     */
    public BackEnd.World getWorld() {
        return world;
    }

    
    
}
