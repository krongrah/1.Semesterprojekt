/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acquaintance;

import java.util.List;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public interface IBackEnd {

    void injectFoundation(IFoundation foundation);

    void play();

    void save();

    void drink();

    void talk(String string);

    void drop(String string);

    boolean search(String string);

    Set<String> inspect(String string);

    boolean arrest(String string);

    int convict(String string);

    Set<String> talkMenu();

    Set<String> arrestMenu();

    Set<String> convictMenu();

    Set<String> searchMenu();

    Set<String> inspectMenu();

    Set<String> dropMenu();

    String getCurrentRoom();

    boolean UIGo(String e);

    Set getMapExits();

    void pickUpAsk(String answer);

    void journal(String string);

    void inventory(String string);

    boolean badgeResponse(String answer);

    List<Integer> getHUD();

    String endScore();

    List<List<String>> getScores();

    void setName(String string);

    int fight();

    int run();

    int calm();

    double getEnemyHealth();

    double getPlayerHealth();

    String[] getEnemyData();

    void printWelcome();

    String getTimeString();

    String getDrunkenness();

    void help();

    String endMessage();

    boolean getSavedGame();

    boolean getIsHobosOnTheMove();
}
