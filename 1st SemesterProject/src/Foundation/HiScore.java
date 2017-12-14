/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Krongrah
 */
class HiScore {

    private ArrayList<String> hiScoreList = new ArrayList<>();
    private File file = new File("HiScore.txt");
    
    
    /**
     * 
     * @return Returns an ArrayList containing all high scores, after retrieving it from the file HiScore.txt.
     */
    ArrayList<String> pullHiScore() {
        
        file.mkdir();//makes directory if none exists
        try {
            
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                hiScoreList.add(input.nextLine());
            }
            return hiScoreList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

   
/**
 * Saves the content of the ArrayList argument the in the file HiScore.txt.
 * @param newList 
 */
    void writeHiScore(ArrayList<String> newList) {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String score : newList) {
                writer.println(score);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    

    
}