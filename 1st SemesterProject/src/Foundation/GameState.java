/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import BackEnd.PC;
import BackEnd.World;
import java.io.Serializable;
import javafx.scene.input.TransferMode;

/**
 *
 * @author jensd
 */
class GameState implements Serializable{
    BackEnd.PC player;
    BackEnd.World world;
    
    GameState(PC player, World world){
    this.player=player;
    this.world=world;
              
    }
}
