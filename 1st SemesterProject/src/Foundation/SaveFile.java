/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

/**
 *
 * @author Krongrah
 */

public class SaveFile implements Serializable{
    
    public boolean saveGame(Object gameState){
        try
        {
          FileOutputStream fileOut = new FileOutputStream("data.ser");
          ObjectOutputStream out = new ObjectOutputStream(fileOut);
          out.writeObject(gameState);
          out.close();
          fileOut.close();
          System.out.println("game saved");

          return true;
        }
        catch (IOException i){
          i.printStackTrace();
        }

        return false;
    }
  
  public Object getSavedGame(){
    Object gameState = new Object();
    
    try{
      FileInputStream fileIn = new FileInputStream("data.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object temp = in.readObject();
      if ((temp instanceof Object)) {
        gameState = (Object)temp;
      }
      in.close();
      fileIn.close();
      return gameState; 
    } 
    catch (FileNotFoundException f){
        System.err.println("File does not exist");
    }
    catch (IOException i){
        System.err.println("IO exception found");
    }
    catch (ClassNotFoundException c){
        System.err.println("Class not found in file");
    } 
    return null;
  }
}
