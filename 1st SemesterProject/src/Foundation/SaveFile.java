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

public class SaveFile {
    
   public boolean saveGame(List aList)
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
      out.writeObject(aList);
      out.close();
      fileOut.close();
      return true;
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }
    return false;
  }
  
  public List loadGame()
  {
    ArrayList aList = new ArrayList();
    
    File desktop = new File(System.getProperty("user.home"), "Desktop");
    try
    {
      FileInputStream fileIn = new FileInputStream(desktop + "\\data\\data.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object temp = in.readObject();
      if ((temp instanceof ArrayList)) {
        aList = (ArrayList)temp;
      }
      in.close();
      fileIn.close();
      return aList;
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
  
  protected boolean saveMap(HashMap aMap)
  {
    File file = new File("src\\resources", "newfile.ser");
    if (!file.exists()) {
      try
      {
        file.createNewFile();
      }
      catch (IOException ex)
      {
        Logger.getLogger(Data.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    try
    {
      FileOutputStream fileOut = new FileOutputStream(file);
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(aMap);
      out.close();
      fileOut.close();
      return true;
    }
    catch (IOException i)
    {
      i.printStackTrace();
    }
    return false;
  }
  
  protected HashMap loadMap()
  {
    HashMap<String, String> aMap = new HashMap();
    
    File file = new File("src\\resources", "newfile.ser");
    try
    {
      FileInputStream fileIn = new FileInputStream(file);
      ObjectInputStream in = new ObjectInputStream(fileIn);
      Object temp = in.readObject();
      if ((temp instanceof HashMap)) {
        aMap = (HashMap)temp;
      }
      in.close();
      fileIn.close();
      return aMap;
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
