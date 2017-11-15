/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd.WorldFill;

import BackEnd.WorldFill.Clue;

/**
 *
 * @author Krongrah
 */
public class HostileNPC extends NPC {

    private int health;
    private int damage;
    private double aggression;

    public HostileNPC(String newName, Dialogue dialogue, Clue clue, int clueRelease, int health, int damage, double aggression) {
        super(newName, dialogue, clue, clueRelease);
        this.health = health;
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public double getAggression() {
        return aggression;
    }

    public void calmDown() {
        aggression = -1;
    }
}
