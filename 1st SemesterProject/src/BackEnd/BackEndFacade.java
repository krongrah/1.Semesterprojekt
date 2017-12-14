/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import Acquaintance.IBackEnd;
import Acquaintance.IFoundation;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Krongrah
 */
public class BackEndFacade implements IBackEnd {

    IFoundation foundation;
    Game game = new Game();

    /**
     * injects the foundation argument into this object
     *
     * @param foundation
     */
    @Override
    public void injectFoundation(IFoundation foundation) {
        this.foundation = foundation;
    }

    /**
     * injects the foundation further down
     */
    @Override
    public void play() {
        game.getHiScoreManager().pull(foundation);
    }

    /**
     * calls game.talk with the string argument as argument
     *
     * @param string
     */
    @Override
    public void talk(String string) {
        game.talk(string);
    }

    /**
     *
     * @param string
     * @return Returns game.search with the string argument as argument
     */
    @Override
    public boolean search(String string) {
        return game.search(string);
    }

    /**
     * calls game.pickup with answer argument as argument
     *
     * @param answer
     */
    public void pickUpAsk(String answer) {
        game.pickup(answer);
    }

    /**
     *
     * @param string
     * @return returns game.inspect with the string argument as argument
     */
    @Override
    public Set<String> inspect(String string) {
        return game.inspect(string);

    }

    /**
     *
     * @param string
     * @return returns game.convict with the string argument as argument
     */
    @Override
    public int convict(String string) {
        return game.convict(string);
    }

    /**
     *
     * @param string
     * @return returns game.arrest with the string argument as argument
     */
    @Override
    public boolean arrest(String string) {
        return game.arrest(string);
    }

    /**
     * calls game.drink
     */
    @Override
    public void drink() {
        game.drink();
    }

    /**
     * calls foundation.save with game.save as argument
     */
    @Override
    public void save() {
        foundation.save(game.save());
    }

    /**
     *
     * @return returns game.talkMenu
     */
    @Override
    public Set<String> talkMenu() {
        return game.talkMenu();
    }

    /**
     *
     * @return returns game.arrestMenu
     */
    @Override
    public Set<String> arrestMenu() {
        return game.arrestMenu();
    }

    /**
     *
     * @return returns game.convictMenu
     */
    @Override
    public Set<String> convictMenu() {
        return game.convictMenu();
    }

    /**
     *
     * @return returns game.searchMenu
     */
    @Override
    public Set<String> searchMenu() {
        return game.searchMenu();
    }

    /**
     *
     * @return returns game.inspectMenu
     */
    @Override
    public Set<String> inspectMenu() {
        return game.inspectMenu();
    }

    /**
     *
     * @return returns game.getPlayer().getRoom().getRoomName
     */
    @Override
    public String getCurrentRoom() {
        return game.getPlayer().getRoom().getRoomName();
    }

    /**
     *
     * @return game.getPlayer().getRoom().getExit().keySet
     */
    @Override
    public Set getMapExits() {
        return game.getPlayer().getRoom().getExit().keySet();
    }

    /**
     *
     * @param e
     * @return returns game.UIGo with e argument as argument
     */
    @Override
    public boolean UIGo(String e) {
        return game.UIGo(e);
    }

    /**
     * calls game.journal with string argument as argument
     *
     * @param string
     */
    @Override
    public void journal(String string) {
        game.journal(string);
    }

    /**
     * calls game.inventory with string argument as argument
     *
     * @param string
     */
    @Override
    public void inventory(String string) {
        game.inventory(string);
    }

    /**
     *
     * @param answer
     * @return returns game.badgeResponse with answer argument as argument
     */
    @Override
    public boolean badgeResponse(String answer) {
        return game.badgeResponse(answer);
    }

    /**
     *
     * @return returns game.getHUD
     */
    @Override
    public List<Integer> getHUD() {
        return game.getHUD();
    }

    /**
     *
     * @return returns game.endScore
     */
    @Override
    public String endScore() {
        return game.endScore();
    }

    /**
     * calls foundation.getSavedGame and the calls game.setGameState with the
     * return value of the first call
     *
     * @return false if the the first call returns null, else returns true
     */
    @Override
    public boolean getSavedGame() {
        GameState gameState = (GameState) foundation.getSavedGame();
        if (gameState != null) {
            game.setGameState(gameState);
            return true;
        }
        return false;
    }

    /**
     * calls game.drop with the string argument as argument
     *
     * @param string
     */
    @Override
    public void drop(String string) {
        game.drop(string);
    }

    /**
     *
     * @return returns game.dropMenu
     */
    @Override
    public Set<String> dropMenu() {
        return game.dropMenu();
    }

    /**
     * returns game.getScores
     *
     * @return
     */
    @Override
    public List<List<String>> getScores() {
        return game.getScores();
    }

    /**
     * calls game.setName with string argument as argument
     *
     * @param string
     */
    @Override
    public void setName(String string) {
        game.setName(string);
    }

    /**
     *
     * @return returns game.fight
     */
    @Override
    public int fight() {
        return game.fight();
    }

    /**
     *
     * @return returns game.run
     */
    @Override
    public int run() {
        return game.run();
    }

    /**
     *
     * @return returns game.calm
     */
    @Override
    public int calm() {
        return game.calm();
    }

    /**
     *
     * @return returns game.enemyHealthPercent
     */
    @Override
    public double getEnemyHealth() {
        return game.enemyHealthPercent();
    }

    /**
     *
     * @return returns game.playerHealthPercent
     */
    @Override
    public double getPlayerHealth() {
        return game.playerHealthPercent();
    }

    /**
     *
     * @return returns game.getEnemyData
     */
    @Override
    public String[] getEnemyData() {
        return game.getEnemyData();
    }

    /**
     * calls game.printWelcome
     */
    @Override
    public void printWelcome() {
        game.printWelcome();
    }

    /**
     *
     * @return returns game.getTimeString
     */
    @Override
    public String getTimeString() {
        return game.getTimeString();
    }

    /**
     *
     * @return returns game.getDrunkenness
     */
    @Override
    public String getDrunkenness() {
        return game.getDrunkenness();
    }

    /**
     * calls game.help
     */
    @Override
    public void help() {
        game.help();
    }

    /**
     *
     * @return returns game.endMessage
     */
    @Override
    public String endMessage() {
        return game.endMessage();
    }

    /**
     *
     * @return returns game.getIsHobosOnTheMove
     */
    @Override
    public boolean getIsHobosOnTheMove() {
        return game.getIsHobosOnTheMove();
    }

}
