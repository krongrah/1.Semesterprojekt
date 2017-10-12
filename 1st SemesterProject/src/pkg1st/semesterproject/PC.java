/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1st.semesterproject;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public class PC {
    //player attributes
    private int points;
    private Set<Item>inventory=new HashSet<>();
    private Set<Item>desk=new HashSet<>();
    private Set<Clue>Cluelist=new HashSet<>();
    private int maxInventoryCapacity=2;
    
    //constructor
    PC(){
    points=100;
    }
    
    //Checking methods
    public void checkInventory(){
    for (Item thing:inventory){
        System.out.println(thing.getName());
    }
    }
    public void checkDesk(){
        if (newGame.getRoom()==station){
        for (Item thing:desk){
        System.out.println(thing.getName());
        }       
        }else{
        System.out.println("You can't check your desk from here.");  
        }
    }
    public void checkClueList(){
    for (Clue thing:Cluelist){
        System.out.println(thing.getName());
    }
    }
    
    //getters for the descriptions
    public void inspectItem(Item thing){
        if(inventory.contains(thing)){
        System.out.println(thing.getDescription());
        }else{
            System.out.println("You can't find it in your bag.");
        }
        }
    
    public void inspectClue(Clue thing){
        if(Cluelist.contains(thing)){
        System.out.println(thing.getDescription());
        }else{
            System.out.println("You don't know about that yet.");
        }
        }
    
    
    //methods for moving items between inventory and desk
    public void moveToInventory(Item thing){
    if(inventory.size()<=maxInventoryCapacity){
    inventory.add(thing);
    desk.remove(thing);
        System.out.println("You sucessfully moved the item from there to here.");
    }else{
        System.out.println("Your bag is too small, or you just don't want to carry it.");
    }
    }
    
    public void moveToDesk(Item thing){
    inventory.remove(thing);
    desk.add(thing);
        System.out.println("You sucessfully moved the item from here to there.");
    }
    
    //methods for adding to cluelist and inventory
    public void addToInventory(Item thing){
        
        if(thing.getCollectible()){
                System.out.println("You placed it in your bag.");
                //todo
                newGame.currentRoom.ItemsInRoom.remove(thing);
                inventory.add(thing);}
        else{
                System.out.println("You can't seem to get a hold of it.");
            }
    
    }
    
    public void addToCluelist(Clue thing ){
    Cluelist.add(thing);
        System.out.println("You noted the clue down.");
    }
    
    }
    
    
    

