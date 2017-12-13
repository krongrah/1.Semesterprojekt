/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import BackEnd.GameState;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Krongrah
 */

public class SaveFile {
    GameState savedGame;
    
    public void saveFile(GameState savedGame) throws FileNotFoundException, ClassNotFoundException, IOException{
        try {
            
            // write object to file
            FileOutputStream fos = new FileOutputStream("SaveFile.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(savedGame);
            oos.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public GameState loadFile(){
        
       // read object from file
        try {
            FileInputStream fis = new FileInputStream("SaveFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            savedGame = (GameState) ois.readObject();
            ois.close();
            
        } catch (IOException ex) {
            System.out.println("Error while loading.");
        } catch (ClassNotFoundException ex) {
        }
        
        return savedGame;
    }
}
