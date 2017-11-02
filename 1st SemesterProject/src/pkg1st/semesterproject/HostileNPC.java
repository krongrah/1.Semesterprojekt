/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

/**
 *
 * @author Krongrah
 */
public class HostileNPC extends NPC{
    private int health;
    private int damage;
    
    public HostileNPC(String newName, Dialogue dialogue, Clue clue, int clueRelease, int health, int damage) {
        super(newName, dialogue, clue, clueRelease);
        this.health=health;
        this.damage=damage;
    }
    public int getHealth(){
    return health;
    }
    public int getDamage(){
    return damage;
    }
}
