/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IFoundation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Krongrah
 */
class HiScoreManager {

    private List<String> goodHiScore = new ArrayList<>();
    private List<String> badHiScore = new ArrayList<>();
    private IFoundation foundation;
    private int hiScoreMax = 5;

    /**
     * Stores combines the good and the bad high score lists into one, which is
     * then stored in the foundation.
     */
    private void store() {
//    ArrayList<String> combinedList=new ArrayList<>();
//    for(String string1:goodHiScore){
//    combinedList.add(string1);
//    for(String string2:badHiScore){
//    combinedList.add(string2);
//    foundation.saveHiScoreList(combinedList);
//    }}
    }

    /**
     * retrieves a list of high scores from the foundation and splits it into
     * the good and the bad list.
     */
    void retrieve() {
        //retrive the combined list and split it into good and bad.
//        ArrayList<String>list=foundation.getHiScoreList();
//        for(int i=0;i<hiScoreMax;i++){
//        goodHiScore.add(list.get(i));
//        }
//        for(int j=hiScoreMax;j<2*hiScoreMax;j++){
//        goodHiScore.add(list.get(j));
//        }

    }

    /**
     * injects the foundation into this class.
     *
     * @param foundation
     */
    void pull(IFoundation foundation) {
        this.foundation = foundation;
    }

    /**
     * Adds a score to the relevant high score list and then sorts and stores
     * the lists.
     *
     * @param name is the name of the player.
     * @param points is the amount of points the player has collected.
     */
    void addScore(String name, int points) {
        if (points < 100) {
            String pointString = String.format("%02d", (100 - points));
            badHiScore.add(pointString + "% " + name);
        } else {
            String pointString = String.format("%02d", (points - 100));
            goodHiScore.add(pointString + "% " + name);
        }
        sort();
        store();
    }

    /**
     *
     * @return returns a list containing two lists of strings, with the good
     * High score on index 0, and the bad one on index 1.
     */
    List<List<String>> getScores() {
        List<List<String>> scores = new ArrayList<>();
        scores.add(goodHiScore);
        scores.add(badHiScore);
        return scores;
    }

    /**
     * Sorts both high score list via a borrowed sort method, then reverses them
     * to get the top on index 0. finally it cuts of the bottom of the lists, so
     * only the best (hiScoreMax) scores are stored.
     */
    private void sort() {
        Collections.sort(goodHiScore);
        Collections.sort(badHiScore);
        Collections.reverse(goodHiScore);
        Collections.reverse(badHiScore);
        if (goodHiScore.size() > hiScoreMax) {
            goodHiScore = goodHiScore.subList(0, hiScoreMax);
        }
        if (badHiScore.size() > hiScoreMax) {
            badHiScore = badHiScore.subList(0, hiScoreMax);
        }
    }

}
