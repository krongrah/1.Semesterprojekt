/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Krongrah
 */

class SaveFile {
    static GameState savedGame = new GameState();
    
    public static void saveFile() throws IOException{
        try(ObjectOutputStream oos = new ObjectOutputStream(
          new FileOutputStream(new File("saveFile.data")))) {
                oos.writeObject(savedGame);
        }
    }
}

