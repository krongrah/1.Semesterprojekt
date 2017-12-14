/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.PieChart.Data;

/**
 *
 * @author Krongrah
 */

public class SaveFile implements Serializable{
    
   public void saveGame(Object gameState)
  {
    File desktop = new File(System.getProperty("user.home"), "Desktop");
    
    String folderName = "data";
    
    File filePath = new File(desktop.toString(), folderName);
    if (!filePath.exists()) {
      filePath.mkdirs();
    }
    try
    {
      FileOutputStream fileOut = new FileOutputStream(filePath + "\\data.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(gameState);
      out.close();
      fileOut.close();
      System.out.println("game saved");
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }
  }
  
  public Object getSavedGame()
  {
    Object gameState = new Object();
    
    File desktop = new File(System.getProperty("user.home"), "Desktop");
    try
    {
      FileInputStream fileIn = new FileInputStream(desktop + "\\data\\data.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object temp = in.readObject();
      if ((temp instanceof Object)) {
        gameState = (Object)temp;
      }
      in.close();
      fileIn.close();
      return gameState; 
    } catch (FileNotFoundException f) 
    {
        f.printStackTrace();
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }
    catch (ClassNotFoundException c)
    {
      c.printStackTrace();
    } 
    return null;
  }
}
