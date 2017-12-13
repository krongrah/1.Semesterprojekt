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
    GameState savedGame = new GameState();
    
    public void saveFile() throws IOException{
        try (
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SaveFile"))){
                oos.writeObject(savedGame);
            }
    }
    
    public GameState loadFile() throws Exception{
        System.out.println("Loading..");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("SaveFile"))){
            return (GameState) ois.readObject();
        }
    }
}
