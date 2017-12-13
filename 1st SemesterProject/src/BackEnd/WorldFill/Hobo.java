/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

/**
 *
 * @author sebastian
 */
public class Hobo extends HostileNPC implements Serializable {

    public Hobo(String newName, Dialogue dialogue, Clue clue, int clueRelease, int health, int damage, double aggression) {
        super(newName, dialogue, clue, clueRelease, health, damage, aggression);
    }
    
    public Hobo(String newName, Dialogue dialogue, Clue clue, int clueRelease) {
        super(newName, dialogue, clue, clueRelease);
        super.setHealth(50); 
        super.setDamage(7);
        super.setAggression(0);
    }
    
     /**
     * @param health the health to set
     */
    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param damage the damage to set
     */
    @Override
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
