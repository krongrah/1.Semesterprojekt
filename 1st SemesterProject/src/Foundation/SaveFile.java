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
public class SaveFile implements Serializable {

    /**
     * Gets a GameState which is written to a file in the game folder. If it is
     * succesfull it prints out "Game saved", if not it says "Could not save"
     *
     * @param gameState the GameState the player wants saved.
     */
    void saveGame(Object gameState) {
        try {
            FileOutputStream fileOut = new FileOutputStream("data.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(gameState);
            out.close();
            fileOut.close();
            System.out.println("Game saved");

        } catch (IOException i) {
            i.printStackTrace();
            System.out.println("Could not save");
        }

    }

    /**
     * Takes the previous written gamestate, and imports it as an Object. If
     * successfull it return the gameState. 
     * If there is no file, it prints an
     * error "File does not exist" and null 
     * If the file is empty or corrpted it.
     * If the file can't be accessed, but is present it Prints "IO exception found"
     * prints "Class not found in file" and null
     * @return GameState
     */
    Object getSavedGame() {
        Object gameState = new Object();

        try {
            FileInputStream fileIn = new FileInputStream("data.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object temp = in.readObject();
            if ((temp instanceof Object)) {
                gameState = (Object) temp;
            }
            in.close();
            fileIn.close();
            return gameState;
        } catch (FileNotFoundException f) {
            System.err.println("File does not exist");
        } catch (IOException i) {
            System.err.println("IO exception found");
        } catch (ClassNotFoundException c) {
            System.err.println("Class not found in file");
        }
        return null;
    }
}
