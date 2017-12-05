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

/**
 *
 * @author Krongrah
 */

public class SaveFile {
    static GameState savedGame = new GameState();
    
    public static void saveFile() throws IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream("saveFile.txt"));
                oos.writeObject(savedGame);
        } 
        catch (IOException e) {
            System.out.println("Error while saving.");
            e.printStackTrace(System.out);
        }
    }
    
    public static void loadFile(){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream("saveFile.txt");
            ois = new ObjectInputStream(fis);
        } 
        catch (IOException ex) {
            System.out.println("Error while loading.");
        }
    }
}

