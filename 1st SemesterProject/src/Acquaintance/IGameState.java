/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import BackEnd.PC;
import BackEnd.World;

/**
 *
 * @author jensd
 */
public interface IGameState {
    PC player = null;
    World world = null;
    
    public BackEnd.PC getPlayer();
    
    public BackEnd.World getWorld();
}
