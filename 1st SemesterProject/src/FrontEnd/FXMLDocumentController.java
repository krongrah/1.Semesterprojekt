/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import Acquaintance.IBackEnd;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Krongrah
 */
public class FXMLDocumentController implements Initializable {

    private IBackEnd backEnd;
    private List<String> ask = Arrays.asList("Yes", "No");
    @FXML
    private TextArea textOutput;
    @FXML
    private ProgressBar badCopBar;
    @FXML
    private ProgressBar goodCopBar;
    @FXML
    private Button convict;
    @FXML
    private ListView<String> arrestList;
    @FXML
    private ListView<String> talkList;
    @FXML
    private ListView<String> searchList;
    @FXML
    private ListView<String> convictList;
    @FXML
    private ListView<String> inspectList;
    @FXML
    private ImageView arrowNorth;
    @FXML
    private ImageView arrowWest;
    @FXML
    private ImageView arrowSouth;
    @FXML
    private ImageView roomPicture;
    @FXML
    private ImageView arrowEast;
    @FXML
    private ListView<String> pickUpList;
    @FXML
    private ListView<String> inventoryList;
    @FXML
    private ListView<String> journalList;
    @FXML
    private ListView<String> badgeList;
    @FXML
    private BorderPane loseScreen;
    @FXML
    private TextArea loseText;
    @FXML
    private TextField loseScore;
    @FXML
    private BorderPane winScreen;
    @FXML
    private TextField winScore;
    @FXML
    private TextArea winText;
    @FXML
    private ListView<String> winGoodHiScore;
    @FXML
    private ListView<String> winBadHighScore;
    @FXML
    private StackPane stackPane;
    @FXML
    private BorderPane introScreen;
    @FXML
    private GridPane fightScreen;
    @FXML
    private ProgressBar playerHealth;
    @FXML
    private ProgressBar enemyHealth;
    @FXML
    private TextArea fightOutput;
    @FXML
    private ListView<String> dropList;
    @FXML
    private BorderPane mapPane;
    @FXML
    private BorderPane hiScoreScreen;
    @FXML
    private ListView<String> GoodScore;
    @FXML
    private ListView<String> badScore;
    @FXML
    private Button newGame;
    @FXML
    private TextField nameField;
    @FXML
    private Button loadGame;
    @FXML
    private Button hiScore;
    @FXML
    private Button quit;
    @FXML
    private Button doneButton;
    @FXML
    private Label labelStartHealt;
    @FXML
    private Label startDamage;
    @FXML
    private Label labelName;
    @FXML
    private ImageView fightImage;
    @FXML
    private Label watch;
    @FXML
    private Label drunkMeter;

    //All images used
    private Image Bar = new Image("file:src/Texures/Bar.png");
    private Image PD = new Image("file:src/Texures/PD.png");
    private Image Court = new Image("file:src/Texures/Court.png");
    private Image CrimeScene = new Image("file:src/Texures/Crimescene.png");
    private Image hoboAlley = new Image("file:src/Texures/Hoboalley.png");
    private Image Home = new Image("file:src/Texures/Home.png");
    private Image Jail = new Image("file:src/Texures/Jail.png");
    private Image PartnerHouse = new Image("file:src/Texures/PartnerHouse.png");
    private Image rightstreet = new Image("file:src/Texures/rightstreet.png");
    private Image Leftstreet = new Image("file:src/Texures/Leftstreet.png");

    private Image combatImageDirty = new Image("file:src/Texures/combatImageDirty.png");
    private Image combatImageHeroin = new Image("file:src/Texures/combatImageHeroin.png");
    private Image combatImageInsane = new Image("file:src/Texures/combatImageInsane.png");
    private Image combatImageNoTeeth = new Image("file:src/Texures/combatImageNoTeeth.png");
    private Image combatImageWife = new Image("file:src/Texures/combatImageWife.png");

    /**
     * initializes the controller also redirects all System.out inputs from the
     * console to the textAreas textOutput and fightOutput
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        OutputStream o = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textOutput.appendText(String.valueOf((char) b));
                textOutput.setWrapText(true);
                fightOutput.appendText(String.valueOf((char) b));
                fightOutput.setWrapText(true);
            }
        };
        System.setOut(new PrintStream(o, true));
        stackPane.setVisible(false);
        introScreen.setVisible(true);
    }

    /**
     * hides the menu ListViews
     */
    private void closeMenus() {
        stackPane.setVisible(true);
        arrestList.setVisible(false);
        talkList.setVisible(false);
        searchList.setVisible(false);
        convictList.setVisible(false);
        inspectList.setVisible(false);
        pickUpList.setVisible(false);
        inventoryList.setVisible(false);
        journalList.setVisible(false);
        badgeList.setVisible(false);

    }

    /**
     * Hides all directional arrows
     */
    private void resetArrows() {
        arrowEast.setVisible(false);
        arrowNorth.setVisible(false);
        arrowSouth.setVisible(false);
        arrowWest.setVisible(false);
    }

    /**
     * injects the backEnd argument into this controller
     *
     * @param backEnd
     */
    void importBackEnd(IBackEnd backEnd) {
        this.backEnd = backEnd;
    }

    /**
     * handles the activation of the Talk button
     *
     * @param event
     */
    @FXML
    private void talkGui(ActionEvent event) {
        closeMenus();
        Set<String> set = backEnd.talkMenu();
        if (set != null) {
            talkList.setVisible(true);
            talkList.setItems(FXCollections.observableList(new ArrayList(set)));
        }
    }

    /**
     * handles the activation of the Search button
     *
     * @param event
     */
    @FXML
    private void searchGui(ActionEvent event) {
        closeMenus();
        Set<String> set = backEnd.searchMenu();
        if (set != null) {
            searchList.setVisible(true);
            searchList.setItems(FXCollections.observableList(new ArrayList(set)));
        }
    }

    /**
     * handles the activation of the Convict button
     *
     * @param event
     */
    @FXML
    private void convictGUI(ActionEvent event) {
        closeMenus();
        Set<String> set = backEnd.convictMenu();
        if (set != null) {
            convictList.setVisible(true);
            convictList.setItems(FXCollections.observableList(new ArrayList(set)));
        }
    }

    /**
     * handles the activation of the Drink button
     *
     * @param event
     */
    @FXML
    private void drinkGUI(ActionEvent event) {
        closeMenus();
        backEnd.drink();
        updateHUD();
    }

    /**
     * handles the activation of the Arrest button
     *
     * @param event
     */
    @FXML
    private void arrestGUI(ActionEvent event) {
        closeMenus();
        List<String> list = new ArrayList(backEnd.arrestMenu());
        if (!list.isEmpty()) {

            arrestList.setVisible(true);
            arrestList.setItems(FXCollections.observableList(list));
        }
    }

    /**
     * handles the activation of the Save button
     *
     * @param event
     */
    @FXML
    private void saveGUI(ActionEvent event) {
        closeMenus();
        backEnd.save();
        updateHUD();

    }

    /**
     * handles the activation of the Inspect button
     *
     * @param event
     */
    @FXML
    private void inspectGUI(ActionEvent event) {
        closeMenus();
        inspectList.setVisible(true);
        inspectList.setItems(FXCollections.observableList(new ArrayList(backEnd.inspectMenu())));

    }

    /**
     * handles the selection in the talkList
     *
     * @param event
     */
    @FXML
    private void talkMenuSelect(MouseEvent event) {
        if (input(talkList) != null) {
            backEnd.talk(input(talkList));
            talkList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * handles the selection in the arrestList
     *
     * @param event
     */
    @FXML
    private void arrestMenuSelect(MouseEvent event) {
        if (input(arrestList) != null) {
            if (input(arrestList) != "No one") {    //todo is this okay?
                if (backEnd.arrest(input(arrestList))) {
                    convict.setVisible(true);
                    startRooms();
                } else {
                    showLoseScreen();
                }
            }
            arrestList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * handles the selection in the searchList
     *
     * @param event
     */
    @FXML
    private void searchMenuSelect(MouseEvent event) {
        if (input(searchList) != null) {
            if (backEnd.search(input(searchList))) {
                pickUpList.setVisible(true);
                pickUpList.setItems(FXCollections.observableList(ask));

            } else {
                updateHUD();
            }
            searchList.setVisible(false);

        }
    }

    /**
     * handles the selection in the convictList
     *
     * @param event
     */
    @FXML
    private void convictMenuSelect(MouseEvent event) {
        if (input(convictList) != null) {
            int convictResult = backEnd.convict(input(convictList));

            if (convictResult == 1) {
                showWinScreen();
            } else if (convictResult == 2) {
                badgeList.setVisible(true);
                badgeList.setItems(FXCollections.observableList(ask));
            } else {
                convictList.setVisible(false);
                updateHUD();
            }
        }
    }

    /**
     * handles the selection in the inspectList
     *
     * @param event
     */
    @FXML
    private void inspectMenuSelect(MouseEvent event) {
        if (input(inspectList) != null) {
            ObservableList list = FXCollections.observableList(new ArrayList(backEnd.inspect(input(inspectList))));
            if (!list.isEmpty()) {
                if (input(inspectList).equals("Inventory")) {
                    inventoryList.setVisible(true);
                    inventoryList.setItems(list);
                } else {
                    journalList.setVisible(true);
                    journalList.setItems(list);

                }
                backEnd.inspect(input(inspectList));
                inspectList.setVisible(false);
            } else {
                inspectList.setVisible(false);
            }
        }
    }

    /**
     * updates the shown image to the image of the current room
     */
    private void updateRoomImage() {
        switch (backEnd.getCurrentRoom()) {
            case "Bar":
                roomPicture.setImage(Bar);
                break;
            case "Left Street":
                roomPicture.setImage(Leftstreet);
                break;
            case "Home":
                roomPicture.setImage(Home);
                break;
            case "Police Department":
                roomPicture.setImage(PD);
                break;
            case "Partner's Home":
                roomPicture.setImage(PartnerHouse);
                break;
            case "Jail":
                roomPicture.setImage(Jail);
                break;
            case "Court":
                roomPicture.setImage(Court);
                break;
            case "Hobo Alley":
                roomPicture.setImage(hoboAlley);
                break;
            case "Right Street":
                roomPicture.setImage(rightstreet);
                break;
            case "Crime Scene":
                roomPicture.setImage(CrimeScene);
                break;
            default:
                break;
        }

    }

    /**
     * Calls updateRoomImage and resetArrows, and the displays the relevant
     * arrows
     */
    void startRooms() {
        updateRoomImage();
        resetArrows();
        for (Iterator it = backEnd.getMapExits().iterator(); it.hasNext();) {
            String exit = (String) it.next();
            switch (exit) {
                case "north":
                    arrowNorth.setVisible(true);
                    break;
                case "east":
                    arrowEast.setVisible(true);
                    break;
                case "south":
                    arrowSouth.setVisible(true);
                    break;
                case "west":
                    arrowWest.setVisible(true);
                    break;
            }
        }
    }

    /**
     * handles the activation of the arrowNorth
     *
     * @param event
     */
    @FXML
    private void goArrowNorth(MouseEvent event) {
        if (backEnd.UIGo("north")) {
            combat();
        }
        startRooms();
        updateHUD();
    }

    /**
     * handles the activation of the arrowWest
     *
     * @param event
     */
    @FXML
    private void goArrowWest(MouseEvent event) {
        if (backEnd.UIGo("west")) {
            combat();
        }
        startRooms();
        updateHUD();
    }

    /**
     * handles the activation of the arrowSouth
     *
     * @param event
     */
    @FXML
    private void goArrowSouth(MouseEvent event) {
        if (backEnd.UIGo("south")) {
            combat();
        }
        startRooms();
        updateHUD();
    }

    /**
     * handles the activation of the arrowEast
     *
     * @param event
     */
    @FXML
    private void goArrowEast(MouseEvent event) {

        if (backEnd.UIGo("east")) {
            combat();
        }
        startRooms();
        updateHUD();
    }

    /**
     * handles the selection in the pickUpList
     *
     * @param event
     */
    @FXML
    private void pickUpSelect(MouseEvent event) {
        if (input(pickUpList) != null) {
            backEnd.pickUpAsk(input(pickUpList));
            pickUpList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * handles the selection in the inventorytList
     *
     * @param event
     */
    @FXML
    private void inventorySelect(MouseEvent event) {
        if (input(inventoryList) != null) {
            backEnd.inventory(input(inventoryList));
            inventoryList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * handles the selection in the journalList
     *
     * @param event
     */
    @FXML
    private void journalSelect(MouseEvent event) {
        if (input(journalList) != null) {
            backEnd.journal(input(journalList));
            journalList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * handles the selection in the badgeList
     *
     * @param event
     */
    @FXML
    private void badgeSelect(MouseEvent event) {
        if (input(badgeList) != null) {
            backEnd.badgeResponse(input(badgeList));
            if (input(badgeList).equals("No")) {
                closeMenus();
                showWinScreen();
            } else {
                badgeList.setVisible(false);
            }
            updateHUD();
        }
    }

    /**
     * displays the winScreen, and sets the relevant lists and text on the
     * winScreen
     */
    private void showWinScreen() {

        winScreen.setVisible(true);
        winScore.setText(backEnd.endScore());
        mapPane.setVisible(false);
        winGoodHiScore.setItems(FXCollections.observableList(backEnd.getScores().get(0)));
        winBadHighScore.setItems(FXCollections.observableList(backEnd.getScores().get(1)));
        winText.setText(backEnd.endMessage());
        winText.setWrapText(true);
    }

    /**
     * displays the loseScreen, and sets the relevant text on the loseScreen
     */
    private void showLoseScreen() {
        loseScreen.setVisible(true);
        loseScore.setText(backEnd.endScore());
        loseText.setText(backEnd.endMessage());
        loseText.setWrapText(true);
    }

    /**
     * updates the data displayed on the heads up display
     */
    void updateHUD() {
        stackPane.setVisible(false);
        watch.setText(backEnd.getTimeString());
        drunkMeter.setText(backEnd.getDrunkenness());
        if (backEnd.getHUD().get(1) == null) {
            badCopBar.setProgress(backEnd.getHUD().get(2) / 100.0);
            goodCopBar.setProgress(0);
        } else {
            goodCopBar.setProgress(backEnd.getHUD().get(1) / 100.0);
            badCopBar.setProgress(0);
        }
        if (backEnd.getHUD().get(0) / 100.0 <= 0 || backEnd.getHUD().get(0) / 100.0 >= 1.0) {
            showLoseScreen();
        }
    }

    /**
     * closes the game
     *
     * @param event
     */
    @FXML
    private void quit(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Displays the controls needed to get the players name, and hides all
     * others on the introScreen
     *
     * @param event
     */
    @FXML
    private void newGame(ActionEvent event) {
        nameField.setVisible(true);
        newGame.setVisible(false);
        loadGame.setVisible(false);
        hiScore.setVisible(false);
        quit.setVisible(false);
        doneButton.setVisible(true);
    }

    /**
     * loads the saved game and displays it if of exists, else displays that no
     * file has been found and disarms the button
     *
     * @param event
     */
    @FXML
    private void loadGame(ActionEvent event) {

        if (backEnd.getSavedGame()) {
            introScreen.setVisible(false);
            startRooms();
            updateHUD();
            if (backEnd.getIsHobosOnTheMove()) {
                convict.setVisible(true);
            }
        } else {
            loadGame.setText("No save file found");
            loadGame.disarm();

        }
    }

    /**
     * displays the hiScoreScreen upon activation of the High Score button
     *
     * @param event
     */
    @FXML
    private void hiScore(ActionEvent event) {
        GoodScore.setItems(FXCollections.observableList(backEnd.getScores().get(0)));
        badScore.setItems(FXCollections.observableList(backEnd.getScores().get(1)));
        hiScoreScreen.setVisible(true);

    }

    /**
     * handles the activation of the fight button
     *
     * @param event
     */
    @FXML
    private void fight(ActionEvent event) {
        int f = backEnd.fight();
        if (f == 2) {
            fightScreen.setVisible(false);
        } else if (f == 0) {
            showLoseScreen();
            fightScreen.setVisible(false);
        } else {
            updateHealth();
        }
    }

    /**
     * handles the activation of the run button
     *
     * @param event
     */
    @FXML
    private void run(ActionEvent event) {
        int f = backEnd.run();
        if (f == 2) {
            fightScreen.setVisible(false);
            startRooms();

        } else if (f == 0) {
            showLoseScreen();
            fightScreen.setVisible(false);
        } else {
            updateHealth();
        }
    }

    /**
     * handles the activation of the calm button
     *
     * @param event
     */
    @FXML
    private void calm(ActionEvent event) {
        int f = backEnd.calm();
        if (f == 2) {
            fightScreen.setVisible(false);
        } else if (f == 0) {
            showLoseScreen();
            fightScreen.setVisible(false);
        } else {
            updateHealth();
        }
    }

    /**
     * updates the health bars of the combatants during combat
     */
    private void updateHealth() {
        playerHealth.setProgress(backEnd.getPlayerHealth());
        enemyHealth.setProgress(backEnd.getEnemyHealth());
    }

    /**
     * changes the screen to the fightScreen
     */
    private void combat() {
        labelName.setText(backEnd.getEnemyData()[0]);
        startDamage.setText(backEnd.getEnemyData()[1]);
        labelStartHealt.setText(backEnd.getEnemyData()[2]);
        switch (backEnd.getEnemyData()[0]) {
            case "Dirty Darryl":
                fightImage.setImage(combatImageDirty);
                break;
            case "Heroin Harry":
                fightImage.setImage(combatImageHeroin);
                break;
            case "No-Teeth Terry":
                fightImage.setImage(combatImageNoTeeth);
                break;
            case "Insane Dwayne":
                fightImage.setImage(combatImageInsane);
                break;
            case "Wife":
                fightImage.setImage(combatImageWife);
                break;
        }
        fightScreen.setVisible(true);
        updateHealth();
    }

    /**
     * Takes a listView of String as input and returns the selected String
     *
     * @param input
     * @return
     */
    private String input(ListView<String> input) {
        String string = input.getSelectionModel().getSelectedItem();
        return string;

    }

    /**
     * handles the activation of the Drop button
     *
     * @param event
     */
    @FXML
    private void dropGUI(ActionEvent event) {
        closeMenus();
        Set<String> set = backEnd.dropMenu();
        if (set != null) {
            dropList.setVisible(true);
            dropList.setItems(FXCollections.observableList(new ArrayList(set)));
        }
    }

    /**
     * handles the selection in the dropList
     *
     * @param event
     */
    @FXML
    private void dropMenuSelect(MouseEvent event) {
        if (input(dropList) != null) {
            backEnd.drop(input(dropList));
            dropList.setVisible(false);
            updateHUD();
        }
    }

    /**
     * hides the hiScoreScreen
     *
     * @param event
     */
    @FXML
    private void back(ActionEvent event) {
        hiScoreScreen.setVisible(false);
    }

    /**
     * hides the introScreen and stores the entered name.
     *
     * @param event
     */
    @FXML
    private void doneButton(ActionEvent event) {

        backEnd.setName(nameField.getText());
        introScreen.setVisible(false);
        backEnd.printWelcome();
    }

    /**
     * handles the activation of the Help button
     *
     * @param event
     */
    @FXML
    private void helpGUI(ActionEvent event) {
        backEnd.help();
    }
}
