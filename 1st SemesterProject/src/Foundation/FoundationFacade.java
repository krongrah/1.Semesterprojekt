/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.util.ArrayList;
import Acquaintance.IFoundation;

/**
 *
 * @author Krongrah
 */
public class FoundationFacade implements IFoundation {

    private HiScore hiScore = new HiScore();
    private SaveFile saveFile = new SaveFile();

    /**
     * calls hiScore.writeHiScore with the list argument as argument
     *
     * @param list
     */
    @Override
    public void saveHiScoreList(ArrayList<String> list) {
        hiScore.writeHiScore(list);
    }

    /**
     *
     * @return Returns hiScore.pullHiScore
     */
    @Override
    public ArrayList<String> getHiScoreList() {
        return hiScore.pullHiScore();
    }

    /**
     * Calls saveFile.saveGame with the object gameState as argument
     *
     * @param gameState
     */
    public void save(Object gameState) {
        saveFile.saveGame(gameState);
    }

    /**
     *
     * @return Returns saveFile.getSavedGame
     */
    @Override
    public Object getSavedGame() {
        return saveFile.getSavedGame();
    }
}
