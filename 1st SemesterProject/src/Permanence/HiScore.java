/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Permanence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Krongrah
 */
class HiScore {

    private List<String> hiScoreList = new ArrayList<>();
    private File file = new File("HiScore.txt");
    private final int max=10;
    
    private void pullHiScore() {
        file.mkdir();//make directory if none exists
        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                hiScoreList.add(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readHiScore() {
        for (String score : hiScoreList) {
            System.out.println(score);
        }
    }

    private void addHiScore(String entry) {
        hiScoreList.add(entry);
    }

    private void sortArray() {

        sort();
        if (hiScoreList.size() == (max+1)) {
            hiScoreList.remove(max);
        }

    }

    private void writeHiScore() {
        try {
            PrintWriter writer = new PrintWriter(file);
            for (String score : hiScoreList) {
                writer.println(score);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String[] sendHiScore(){
    String[] score=new String[hiScoreList.size()]; 
    for(int i=0;i<hiScoreList.size();i++){
    score[i]=hiScoreList.get(i);
    }
    return score;
    }
    
    private void sort(){
    Collections.sort(hiScoreList);
    }
    
    String[] doHiScore(String entry){
    pullHiScore();
    addHiScore(entry);
    sortArray();
    readHiScore();
    writeHiScore();
    return sendHiScore();
    } 
    
}