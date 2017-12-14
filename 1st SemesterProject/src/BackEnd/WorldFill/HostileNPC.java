/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import java.io.Serializable;

/**
 *
 * @author Krongrah
 */
public class HostileNPC extends NPC implements Serializable {

    int health;
    int damage;
    int totalHealth;
    private double aggression;
    
    /**
     * Full constructor for HostileNPC's
     * @param newName Is the name of the HostileNPC
     * @param dialogue The HostileNPC's dialog strings.
     * @param clue the clue given in dialog [If any]
     * @param clueRelease How many times the player has to talk to the NPC before getting a clue.
     * @param totalHealth Health of the Hostile NPC, used for fighting.
     * @param damage The damage the NPC can deal to the player.
     * @param aggression The chance of getting jumped, when you enter a room. 0= not possible, 0.5= 50% chance, 1 = always
     */
    public HostileNPC(String newName, Dialogue dialogue, Clue clue, int clueRelease, int totalHealth, int damage, double aggression) {
        super(newName, dialogue, clue, clueRelease);
        this.totalHealth = totalHealth;
        this.damage = damage;
        this.aggression=aggression;
        this.health=totalHealth;
    }
    
    
     /**
     * constructor for HostileNPCs with predetermined combat values
     * @param newName Is the name of the HostileNPC
     * @param dialogue The HostileNPC's dialog strings.
     * @param clue the clue given in dialog [If any]
     * @param clueRelease How many times the player has to talk to the NPC before getting a clue.
     * health Health of the Hostile NPC, used for fighting. Standard= 50
     * damage The damage the NPC can deal to the player. Standard is 7
     * aggression The chance of getting jumped, when you enter a room. 0= not possible, 0.5= 50% chance, 1 = always.
     * Standard is 0.
     */
    public HostileNPC(String newName, Dialogue dialogue, Clue clue, int clueRelease) {
        super(newName, dialogue, clue, clueRelease);
        this.totalHealth = 50;
        this.damage = 7;
        this.aggression=0;
        this.health=totalHealth;
    }
/**
 * 
 * @return health
 */
    public int getHealth() {
        return health;
    }
/**
 * 
 * @return damage
 */
    public int getDamage() {
        return damage;
    }
/**
 * 
 * @return aggression
 */
    public double getAggression() {
        return aggression;
    }
/**
 * Used make a HostileNPC peaceful
 * @set aggression to -1
 */ 
    public void calmDown() {
        setAggression(-1);
    }

    /**
     * Sets the aggression of the HostileNPC
     * @param aggression the aggression to set
     */
    public void setAggression(double aggression) {
        this.aggression = aggression;
    }
    
    /**
     * The scream yelled before fighting.
     * @return the fight scream in the NPC's dialog.
     */
    
    public String getFightScream(){
    return getDialogue().getFightScream();
    }
    
    /**
     * Sets the health of the HostileNPC to the Argument
     * @param health 
     */
    public void setHealth(int health) {
        this.health = health;
    }
    
    /**
     * 
     * @return Returns the maximum health of the HostileNPC
     */
    public int getTotalHealth(){
    return totalHealth;
    }
    
    /**
     * Sets the health of the HostileNPC to the Argument
     * @param damage 
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
