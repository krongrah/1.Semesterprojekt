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
import jdk.nashorn.internal.codegen.CompilerConstants;

/**
 *
 * @author Krongrah
 */

public class SaveFile {
    GameState savedGame;
    
    public static void saveFile(GameState savedGame) throws IOException{
        this.saveGame=savedGame;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream("saveFile.txt"));
                oos.writeObject(savedGame);
                oos.writeObject(savedGame);
            }
    }
    
    public static GameState loadFile(){
        
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            
            fis = new FileInputStream("saveFile.txt");
            ois = new ObjectInputStream(fis);
            savedGame = (GameState) ois.readObject();
            
            return ;
        } 
        catch (IOException ex) {
            System.out.println("Error while loading.");
        }
    }
}
